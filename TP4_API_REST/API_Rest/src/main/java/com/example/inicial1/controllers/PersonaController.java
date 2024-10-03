package com.example.inicial1.controllers;

import com.example.inicial1.entities.Persona;
import com.example.inicial1.services.PersonaServicesImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@CrossOrigin(origins = "*")//Permitir el acceso a distintos clientes a nuestra api
@RequestMapping("api/v1/personas")
public class PersonaController extends BaseControllerImpl<Persona,PersonaServicesImpl> {

}