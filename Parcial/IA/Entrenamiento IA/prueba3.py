import random
import numpy as np
import tensorflow as tf
from sklearn.model_selection import train_test_split
from sklearn.metrics import confusion_matrix, classification_report
import os
import matplotlib.pyplot as plt
import seaborn as sns
import json  # Importar el módulo json para trabajar con archivos JSON

# Establecer el nivel de log de TensorFlow
os.environ['TF_CPP_MIN_LOG_LEVEL'] = '2'
print("Funcionando")

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

def procesar_json(filename):
    """Lee un archivo JSON y convierte las secuencias de ADN en datos y etiquetas."""
    datos = []
    etiquetas = []

    with open(filename, 'r') as f:
        contenido = json.load(f)
        for ejemplo in contenido:
            dna = ejemplo['secuencia']  # Asumiendo que la secuencia está en la clave 'secuencia'
            etiqueta = ejemplo['etiqueta']  # Asumiendo que la etiqueta está en la clave 'etiqueta'

            datos.append(dna)
            etiquetas.append(int(etiqueta))  # Asegurarse de que la etiqueta sea un entero

    return datos, etiquetas

# Cargar el conjunto de datos desde un archivo JSON
filename = 'conjunto_datos_mutantes.json'  # Cambia el nombre a tu archivo JSON
datos, etiquetas = procesar_json(filename)

# Preprocesar los datos
X = []
y = np.array(etiquetas)

# Convertir ADN en números para todas las secuencias
for dna in datos:  # Procesar todas las secuencias
    secuencia_numerica = []
    for fila in dna:  # Iterar sobre cada fila (cadena) de la secuencia
        secuencia_numerica_fila = [convertirADN(base) for base in fila]  # Convertir cada base a su número
        secuencia_numerica.append(secuencia_numerica_fila)
    X.append(secuencia_numerica)

X = np.array(X)

# Dividir el conjunto de datos
# ... (resto del código sin cambios)

# Dividir el conjunto de datos
if len(X) > 0:
    X_train, X_test, y_train, y_test = train_test_split(X, y, test_size=0.2, random_state=42)

    # Imprimir las secuencias de entrenamiento y sus etiquetas
    """ print("Secuencias de entrenamiento:")
    for seq, label in zip(X_train, y_train):
        print(f"Secuencia: {seq}, Etiqueta: {label}") """

        # Guardar las secuencias de prueba y sus etiquetas en un archivo JSON
    resultado_prueba = []
    for seq, label in zip(X_test, y_test):
        resultado_prueba.append({
            'secuencia': seq.tolist(),  # Convertir el arreglo NumPy a lista
            'etiqueta': int(label)  # Asegurarse de que la etiqueta sea un entero
        })

    # Escribir en un archivo JSON
    output_filename = 'resultado_prueba.json'
    with open(output_filename, 'w') as f:
        json.dump(resultado_prueba, f)
    print(f"Resultados de prueba guardados en {output_filename}.")


    # Crear y entrenar el modelo
    modelo = tf.keras.models.Sequential([
        tf.keras.layers.Input(shape=(X_train.shape[1], X_train.shape[2])),
        tf.keras.layers.Flatten(),
        tf.keras.layers.Dense(64, activation='relu'),
        tf.keras.layers.Dense(32, activation='relu'),
        tf.keras.layers.Dense(1, activation='sigmoid')    
    ])

    optimizer = tf.keras.optimizers.Adam(learning_rate=0.001)

    modelo.compile(optimizer=optimizer, loss='binary_crossentropy', metrics=['accuracy'])

    # Entrenar el modelo
    try:
        modelo.fit(X_train, y_train, epochs=10, batch_size=32)
    except Exception as e:
        print("Error durante el entrenamiento: " + str(e))

    # Evaluar el modelo
    try:
        loss, accuracy = modelo.evaluate(X_test, y_test)
        print(f'Pérdida: {loss}, Precisión: {accuracy}')
    except Exception as e:
        print("Error al evaluar el modelo: " + str(e))

    # Guardar el modelo en formato SavedModel
    try:
        modelo.save("C:\\UTN\\Desarrollo de Software\\Parcial\\IA\\X-MEN.h5")
        print("Modelo guardado con éxito en formato SavedModel.")
    except Exception as e:
        print("Error al guardar el modelo: " + str(e))

    # Predecir las etiquetas para el conjunto de prueba
    y_pred = (modelo.predict(X_test) > 0.4).astype("int32")

    # Total de secuencias analizadas
    total_sec = len(y_test)

    # Contar cuántas predicciones son mutantes (1) y no mutantes (0)
    mutantes_count = np.sum(y_pred)
    no_mutantes_count = total_sec - mutantes_count

    # Imprimir los resultados
    print(f'Total de secuencias analizadas: {total_sec}')
    print(f'Secuecias predichas como mutantes: {mutantes_count}')
    print(f'Secuecias predichas como no mutantes: {no_mutantes_count}')

    # Reporte de clasificación
    print(classification_report(y_test, y_pred))

else:
    print("No hay suficientes datos válidos para entrenar el modelo.")

print("Entrenamiento completado.")
