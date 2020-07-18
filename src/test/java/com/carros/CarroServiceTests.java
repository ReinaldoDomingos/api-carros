package com.carros;

import com.carros.domain.exception.ObjectNotFoundException;
import com.carros.domain.Carro;
import com.carros.domain.CarroService;
import com.carros.domain.dto.CarroDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CarroServiceTests {

    @Autowired
    private CarroService service;

    @Test
    void testSave() {
        Carro carro = new Carro();
        carro.setNome("Ferrari");
        carro.setTipo("esportivos");

        CarroDTO c = service.inserir(carro);

        assertNotNull(c);

        Long id = c.getId();
        assertNotNull(id);

        c = service.getCarroById(id);
        assertNotNull(c);

        assertEquals("Ferrari", c.getNome());
        assertEquals("esportivos", c.getTipo());

        service.delete(id);

        try {
            assertNotNull(service.getCarroById(id));
            fail("O carro não foi excluido");
        } catch (ObjectNotFoundException e) {

        }
    }

    @Test
    void testLista() {
        List<CarroDTO> carros = service.getCarros();

        assertEquals(30, carros.size());
    }

    @Test
    void testListaPorTipo() {
        assertEquals(10, service.getCarrosByTipo("esportivos").size());
        assertEquals(10, service.getCarrosByTipo("classicos").size());
        assertEquals(10, service.getCarrosByTipo("luxo").size());

        assertEquals(0, service.getCarrosByTipo("x").size());
    }

    @Test
    void testGet() {
        CarroDTO c = service.getCarroById(11L);

        assertNotNull(c);


        assertEquals("Ferrari FF", c.getNome());
    }
}
