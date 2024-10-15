import json

def contar_etiquetas(archivo):
    try:
        # Leer el archivo JSON
        with open(archivo, 'r') as f:
            datos = json.load(f)
        
        # Contadores para las etiquetas
        conteo_etiqueta_1 = 0
        conteo_etiqueta_0 = 0

        # Contar etiquetas
        for item in datos:
            if item['etiqueta'] == 1:
                conteo_etiqueta_1 += 1
            elif item['etiqueta'] == 0:
                conteo_etiqueta_0 += 1
        
        # Imprimir resultados
        print(f"Cantidad de etiquetas 1: {conteo_etiqueta_1}")
        print(f"Cantidad de etiquetas 0: {conteo_etiqueta_0}")

    except FileNotFoundError:
        print(f"El archivo {archivo} no se encontró.")
    except json.JSONDecodeError:
        print("Error al decodificar el archivo JSON.")
    except Exception as e:
        print(f"Ocurrió un error: {e}")

# Nombre del archivo
archivo_json = 'conjunto_datos_mutantes.json'

# Llamar a la función
contar_etiquetas(archivo_json)
