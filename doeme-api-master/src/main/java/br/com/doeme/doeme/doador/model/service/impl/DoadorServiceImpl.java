package br.com.doeme.doeme.doador.model.service.impl;

import br.com.doeme.doeme.doador.model.entity.Doador;
import br.com.doeme.doeme.doador.model.repositories.DoadorRepository;
import br.com.doeme.doeme.doador.model.service.DoadorService;
import br.com.doeme.doeme.exceptions.ResourceFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class DoadorServiceImpl implements DoadorService {

    @Autowired
    private DoadorRepository doadorRepository;


    @Override
    public Doador save(Doador doador) throws ResourceFoundException {

        return doadorRepository.save(doador);
    }

    @Override
    public Doador update(Long id, Doador doador) {
        Optional<Doador> doadorById = findById(id);
        if (doadorById.isPresent()) {
            var doadorUpdate = doadorById.get();
            doadorUpdate.update(id, doador);
            return doadorRepository.save(doadorUpdate);
        }

        return new Doador();
    }

    @Override
    public List<Doador> list() {
        return doadorRepository.findAll();
    }

    @Override
    public Optional<Doador> findById(Long id) {
        return doadorRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        doadorRepository.deleteById(id);
    }
}
