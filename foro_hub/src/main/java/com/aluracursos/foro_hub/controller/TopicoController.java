package com.aluracursos.foro_hub.controller;


import com.aluracursos.foro_hub.dto.TopicoRecord;
import com.aluracursos.foro_hub.dto.TopicoResponse;
import com.aluracursos.foro_hub.service.TopicoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/topico")
@Validated
public class TopicoController {

    @Autowired
    private TopicoService topicoService;

    @PostMapping
    public ResponseEntity<TopicoResponse> registrarTopico(@Valid @RequestBody TopicoRecord topicoRecord) {
        TopicoResponse response = topicoService.registrarTopico(topicoRecord);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<TopicoResponse>> listarTopicos() {
        List<TopicoResponse> responses = topicoService.listarTopicos();
        return ResponseEntity.ok(responses);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TopicoResponse> actualizarTopico(@PathVariable Long id, @Valid @RequestBody TopicoRecord topicoRecord) {
        TopicoResponse response = topicoService.actualizarTopico(id, topicoRecord);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarTopico(@PathVariable Long id) {
        topicoService.eliminarTopico(id);
        return ResponseEntity.noContent().build();
    }
}



