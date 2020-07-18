package com.carros.domain;

import com.carros.domain.exception.ObjectNotFoundException;
import com.carros.domain.dto.CarroDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CarroService {
    @Autowired
    CarroRepository repository;

    public List<CarroDTO> getCarros() {
        return repository.findAll().stream().map(CarroDTO::create).collect(Collectors.toList());
    }

    public CarroDTO getCarroById(Long id) {
        Optional<Carro> carro = repository.findById(id);
        return carro.map(CarroDTO::create).orElseThrow(() -> new ObjectNotFoundException("Carro não encontrado"));
    }

    public List<CarroDTO> getCarrosByTipo(String tipo) {
        return repository.findByTipo(tipo).stream().map(CarroDTO::create).collect(Collectors.toList());
    }

    public CarroDTO inserir(Carro carro) {
        Assert.isNull(carro.getId(), "Não foi possivel inserir o registro");

        return CarroDTO.create(repository.save(carro));
    }

    public CarroDTO update(Long id, Carro carro) {
        Assert.notNull(id, "Não foi possivel atualizar o registro");

        Optional<Carro> optional = repository.findById(id);
        if (optional.isPresent()) {
            Carro db = optional.get();

            db.setNome(carro.getNome());
            db.setTipo(carro.getTipo());
            System.out.println("Carro Id " + db.getId());

            repository.save(db);

            return CarroDTO.create(db);
        } else {
            throw new RuntimeException("Não foi possivel atualizar o registro");
        }
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
