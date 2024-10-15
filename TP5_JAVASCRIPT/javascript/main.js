/* //Introducción a JavaScript
//Ejercicio 2 
let a = 5;
let b = 10;
let c = a + b;

console.log("a:",a)
console.log("b:",b)
console.log("c = a+b :",c)

//Ejercicio 3 

let nombre = prompt("Ingresa tu nombre:");

console.log("¡Hola, " + nombre + "!");

//Operadores lógicos y condicionales
//Ejercicio 1

if (a > b) {
  c = a;
} else if (b > a) {
  c = b;
} else {
  c = "Ambos son iguales"; 
}

console.log("a:", a);
console.log("b:", b);
console.log("El mayor valor es:", c);

//Ejercicio 2

let numeroIngresado = prompt("Por favor, ingresa un número:");

numeroIngresado = parseInt(numeroIngresado);

if (numeroIngresado % 2 === 0) {
  alert(`El número ${numeroIngresado} es par`);
} else {
  alert(`El número ${numeroIngresado} es impar`);
}

//Operadores de asignación y bucles
//Ejercicio 1

let numero = 10;

while (numero > 0) {
  console.log(numero); 
  numero--; 
}

console.log(numero);

//Ejercicio 2

let numeroIngresado2;

do {
  
  numeroIngresado2 = prompt("Por favor, ingresa un número mayor a 100:");
  
  
  numeroIngresado2 = parseInt(numeroIngresado2);
  
} while (numeroIngresado2 <= 100 || isNaN(numeroIngresado2)); 

console.log(`Ingresaste un numero mayor a 100: ${numeroIngresado2}`);

//Funciones de JavaScript
//Ejercicio 1

function esPar(numero) {
  return numero % 2 === 0;
}

// Pruebas
console.log(esPar(4));  
console.log(esPar(7));  
console.log(esPar(0));  
console.log(esPar(101)); 
console.log(esPar(22)); 

//Ejercicio 2

function convertirCelsiusAFahrenheit(celsius) {
  let fahrenheit = celsius * 1.8 + 32;
  return fahrenheit;
}

let celsius = 25; // Ejemplo de prueba
console.log(`${celsius} grados Celsius son ${convertirCelsiusAFahrenheit(celsius)} grados Fahrenheit.`);

//Objetos en JavaScript
//Ejercicio 1

let persona = {
  nombre: "Matias",
  edad: 22,
  ciudad: "Mendoza",
  
  
  cambiarCiudad: function(nuevaCiudad) {
    this.ciudad = nuevaCiudad;
  }
};

// Mostrar las propiedades iniciales
console.log(`Nombre: ${persona.nombre}, Edad: ${persona.edad}, Ciudad: ${persona.ciudad}`);

persona.cambiarCiudad("BsAs");

// Mostrar las propiedades actualizadas
console.log(`Nombre: ${persona.nombre}, Edad: ${persona.edad}, Ciudad: ${persona.ciudad}`);

//Ejercicio 2
let libro = {
  titulo: "Cien años de soledad",
  autor: "Gabriel García Márquez",
  año: 2000,

  esAntiguo: function() {
    let añoActual = new Date().getFullYear();
    return añoActual - this.año > 10;
  }
};

if (libro.esAntiguo()) {
  console.log(`El libro "${libro.titulo}" es antiguo.`);
} else {
  console.log(`El libro "${libro.titulo}" es reciente.`);
}

//Arrays
//Ejercicio 1
let numeros = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10];

let numerosMultiplicados = [];

for (let i = 0; i < numeros.length; i++) {
  numerosMultiplicados.push(numeros[i] * 2);
}

console.log("Array original:", numeros);
console.log("Array multiplicado por 2:", numerosMultiplicados);

//Ejercicio 2

let pares = [];

for (let i = 1; i <= 20; i++) {
  if (i % 2 === 0) {
    pares.push(i);
  }
  
  if (pares.length === 10) {
    break;
  }
}

console.log("Primeros 10 números pares:", pares);
 */
//Introducción al DOM
//Ejercicio 1
const button = document.getElementById("boton1")
button.addEventListener("click",()=>{
  cambiarColor()
})

function cambiarColor() {
  let parrafos = document.querySelectorAll("p");
  parrafos.forEach(function(parrafo) {
      parrafo.style.color = "blue";
  });
}
//Ejercicio 2
const button2 = document.getElementById("boton2")
button2.addEventListener("click" ,()=>{
  mostrarAlerta()
})

const mostrarAlerta = () => {
  let valorTexto = document.getElementById("texto").value;
  alert("Valor ingresado: " + valorTexto);
}

//Eventos en DOM
//Ejercicio 1
const lista = document.getElementById("miLista");
const elementosLi = lista.getElementsByTagName("li");
for (let i = 0; i < elementosLi.length; i++) {
  elementosLi[i].addEventListener("click", function() {
  console.log(this.textContent); // Mostrar el texto del elemento clicado en la consola
});
}

//Ejercicio 2
const campoTexto = document.getElementById("campoTexto");
const deshabilitarBtn = document.getElementById("deshabilitarBtn");
const habilitarBtn = document.getElementById("habilitarBtn");

deshabilitarBtn.addEventListener("click", () => {
    campoTexto.disabled = true; 
});

habilitarBtn.addEventListener("click", () => {
    campoTexto.disabled = false; 
    campoTexto.focus(); 
});

//LocalStorage 
//Ejercicio 1
const formulario = document.getElementById("formulario");
const correoInput = document.getElementById("correoInput");
const mensajeDiv = document.getElementById("mensaje");


window.onload = function() {
const correoGuardado = localStorage.getItem("correo");
if (correoGuardado) {
  mostrarCorreo(correoGuardado);
}
};


function mostrarCorreo(correo) {
mensajeDiv.innerHTML = `
<p>Correo guardado: ${correo}</p>
<button id="eliminarBtn">Eliminar Correo Guardado</button>`;

document.getElementById("eliminarBtn").addEventListener("click", eliminarCorreo);
}

formulario.addEventListener("submit", function(event) {
event.preventDefault(); 
const correo = correoInput.value;

localStorage.setItem("correo", correo);
mostrarCorreo(correo);
correoInput.value = ""; 
});


function eliminarCorreo() {
localStorage.removeItem("correo"); 
}