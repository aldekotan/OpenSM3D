import json
import struct

def write_binary(data, output_file):
    """Writes objects data to file"""
    
    with open(output_file, 'wb') as f:
        # Header (2 bytes)
        f.write(struct.pack('>B', 0x0A))
        f.write(struct.pack('>B', 0xB1))
        
        # Objects count (1 byte)
        f.write(struct.pack('>B', len(data)))
        
        for idx, obj in enumerate(data):
            # Check object_id
            if obj['object_id'] != idx:
                raise ValueError(f"object_id mismatch: expected {idx}, got {obj['object_id']}")

            # Main object fields
            f.write(struct.pack('>B', obj['model_source']))
            f.write(struct.pack('>B', obj['unused_field']))
            
            # Write model and texture name
            for field in ['model_name', 'texture_name']:
                value = obj[field].encode('utf-8')
                if len(value) > 255:
                    raise ValueError(f"{field} can't take more than 255 bytes (object {idx})")
                
                f.write(struct.pack('>B', len(value)))
                f.write(value)
            
            # Write additional fields
            if obj['model_source'] == 4:
                # Bot data
                static_data = obj['static_bot_data']
                f.write(struct.pack('>B', static_data['weapon_id']))
                f.write(struct.pack('>B', static_data['anim_time']))
            else:
                # M3G / Sprite data
                lights_data = obj['m3g_data']
                f.write(struct.pack('>B', lights_data['sprite_blend_mode']))
                f.write(struct.pack('>B', lights_data['m3g_id']))
            
            # Model scale
            scale = int(round(obj['scale'] * 100))
            f.write(struct.pack('>i', scale))

def read_str(f):
    return f.read(struct.unpack('>B', f.read(1))[0]).decode('utf-8')

def read_binary(filename):
    """Reads objects data from file"""
    
    with open(filename, 'rb') as f:
        f.read(2)  # Skip header
        
        objs_count = struct.unpack('>B', f.read(1))[0]
        objects = []
        
        for obj_id in range(objs_count):
            obj = {}
            objects.append(obj)
            
            # Store object id to help keep original objects order
            obj['object_id'] = obj_id
            
            # Main object fields
            obj['model_source'] = struct.unpack('>B', f.read(1))[0]
            obj['unused_field'] = struct.unpack('>B', f.read(1))[0]
            
            # Read model and texture name
            obj['model_name'] = read_str(f)
            obj['texture_name'] = read_str(f)
            
            if obj['model_source'] == 4:
                # Bot data
                obj['static_bot_data'] = {
                    'weapon_id': struct.unpack('>B', f.read(1))[0],
                    'anim_time': struct.unpack('>B', f.read(1))[0]
                }
            else:
                # M3G / Sprite data
                obj['m3g_data'] = {
                    'sprite_blend_mode': struct.unpack('>B', f.read(1))[0],
                    'm3g_id':            struct.unpack('>B', f.read(1))[0]
                }
            
            # Scale
            obj['scale'] = struct.unpack('>i', f.read(4))[0] / 100.0
    
    return objects

if __name__ == "__main__":
    import argparse
    
    parser = argparse.ArgumentParser(description='Converts objects file from binary data to json and back')
    parser.add_argument('input', help='Input file')
    parser.add_argument('output', help='Output file')
    
    args = parser.parse_args()
    
    try:
        if args.input.lower().endswith('.json'):
            # Read json
            with open(args.input, 'r', encoding='utf-8') as f:
                data = json.load(f)['objects']
            
            write_binary(data, args.output)
        else:
            # Read binary objects data
            output = args.input + '.json'
            data = read_binary(args.input)
            
            with open(args.output, 'w', encoding='utf-8') as f:
                json.dump(
                    {
                        "_comment": "Object IDs correspond to their original position in binary file (0-based index)",
                        "objects": data
                    }, 
                    f, 
                    ensure_ascii=False, 
                    indent=4
                )
                
        print(f"Succesfully converted {args.input}")
    except Exception as e:
        print(f"Convertation error: {str(e)}")