package br.com.doeme.doeme.doador.v1;

import br.com.doeme.doeme.doador.model.entity.Doador;
import br.com.doeme.doeme.doador.model.service.DoadorService;
import br.com.doeme.doeme.exceptions.ResourceFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/doador")
public class DoadorResources {

    @Autowired
    private DoadorService doadorService;

    @GetMapping
    @ResponseBody
    public ResponseEntity<List<Doador>> list() {
        List<Doador> doadorList = doadorService.list();

        if (doadorList.isEmpty())
            return ResponseEntity.noContent().build();

        return ResponseEntity.ok(doadorList);
    }

    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<Doador> finById(@PathVariable("id") Long id) {
        Optional<Doador> doador = doadorService.findById(id);

        if (!doador.isPresent())
            return ResponseEntity.noContent().build();

        return ResponseEntity.ok(doador.get());
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<Doador> save(@Valid @RequestBody Doador doador) throws ResourceFoundException {
        Doador saved = doadorService.save(doador);

        if (saved == null)
            return ResponseEntity.noContent().build();

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(saved.getId()).toUri();
        return ResponseEntity.created(uri).body(saved);
    }

    @PutMapping("/{id}")
    @ResponseBody
    public ResponseEntity<Doador> update(@PathVariable("id") Long id, @Valid @RequestBody Doador doador) {
        Doador updated = doadorService.update(id, doador);

        if (updated == null)
            return ResponseEntity.noContent().build();

        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> detele(@PathVariable("id") Long id) {
        doadorService.delete(id);

        return ResponseEntity.noContent().build();
    }
}
