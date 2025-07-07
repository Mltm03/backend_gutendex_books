package com.literalura.challenge.by.alura.service;

public interface IConvierteDatos {
<T> T obtenerDatos(String json, Class<T> clase);
}
