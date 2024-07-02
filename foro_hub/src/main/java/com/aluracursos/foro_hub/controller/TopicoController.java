package com.aluracursos.foro_hub.controller;

import com.aluracursos.foro_hub.config.DuplicateTopicException;
import com.aluracursos.foro_hub.dto.TopicoRecord;
import com.aluracursos.foro_hub.dto.TopicoResponse;


import com.aluracursos.foro_hub.service.TopicoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/topico")
@Validated
public class TopicoController {

    @Autowired
    private TopicoService topicoService;

    @PostMapping
    public ResponseEntity<Map<String, Object>> registrarTopico(@Valid @RequestBody TopicoRecord topicoRecord) {
        try {
            TopicoResponse response = topicoService.registrarTopico(topicoRecord);
            Map<String, Object> responseMap = new HashMap<>();
            responseMap.put("topico", Map.of(
                    "id", response.id(),
                    "titulo", response.titulo(),
                    "mensaje", response.mensaje(),
                    "fechaCreacion", response.fechaCreacion(),
                    "status", response.status()
            ));
            responseMap.put("autor", Map.of(
                    "idAutor", response.idAutor(),
                    "nombreAutor", response.nombreAutor()
            ));
            responseMap.put("curso", Map.of(
                    "idCurso", response.idCurso(),
                    "nombreCurso", response.nombreCurso()
            ));
            return ResponseEntity.ok(responseMap);
        } catch (DuplicateTopicException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(Map.of("error", e.getMessage()));
        }
    }

    @GetMapping
    public ResponseEntity<Page<TopicoResponse>> listarTopicos(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "fechaCreacion") String sort,
            @RequestParam(defaultValue = "asc") String order) {
        Page<TopicoResponse> responses = topicoService.listarTopicos(page, size, sort, order);
        return ResponseEntity.ok(responses);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TopicoResponse> actualizarTopico(@PathVariable Long id, @Valid @RequestBody TopicoRecord topicoRecord) {
        TopicoResponse response = topicoService.actualizarTopico(id, topicoRecord);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> eliminarTopico(@PathVariable Long id) {
        topicoService.eliminarTopico(id);
        return ResponseEntity.ok(Map.of("message", "Tópico eliminado"));
    }


    @GetMapping("/cerrados")
    public ResponseEntity<Map<String, Object>> listarTopicosCerrados(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "fechaCreacion") String sort,
            @RequestParam(defaultValue = "asc") String order
    ) {
        try {
            Page<TopicoResponse> responses = topicoService.listarTopicosCerrados(page, size, sort, order);
            return ResponseEntity.ok(Map.of("topicos", responses));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("mensaje", e.getMessage()));
        }
    }
}




