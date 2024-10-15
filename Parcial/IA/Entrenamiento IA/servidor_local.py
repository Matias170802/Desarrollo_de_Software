from flask import Flask, request, jsonify
from flask_cors import CORS  # Importa CORS
import tensorflow as tf
import numpy as np

# Cargar el modelo
modelo = tf.keras.models.load_model("C:\\UTN\\Desarrollo de Software\\Parcial\\IA\\X-MEN.h5")

app = Flask(__name__)
CORS(app)

def convertirADN(base):
    """Convierte una base de ADN a un número entero."""
    if base == 'A':
        return 2
    elif base == 'T':
        return 3
    elif base == 'C':
        return 4
    elif base == 'G':
        return 5
    else:
        raise ValueError(f"Base de ADN no válida: {base}")

@app.route('/predict', methods=['POST'])
def predict():
    data = request.get_json()
    
    # Asumir que data es un JSON con la clave 'secuencia' que contiene la matriz
    matriz = data['secuencia']
    
    # Convertir la matriz de ADN en números
    secuencia_numerica = []
    for fila in matriz:
        secuencia_numerica_fila = [convertirADN(base) for base in fila]
        secuencia_numerica.append(secuencia_numerica_fila)
    
    secuencia_numerica = np.array([secuencia_numerica])  # Añadir una dimensión para el batch

    # Realizar la predicción
    prediccion = (modelo.predict(secuencia_numerica) > 0.5).astype("int32")

    # Devolver la respuesta como JSON
    return jsonify({'mutante': int(prediccion[0][0])})

if __name__ == '__main__':
    app.run(port=5000)  # Cambia el puerto si es necesario
