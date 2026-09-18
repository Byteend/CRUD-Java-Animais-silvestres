package com.example;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AnimalSilvestreCrudTest {

    @Test
    void deveCadastrarListarAtualizarEExcluirAnimal() throws Exception {
        AnimalSilvestreDAO dao = new AnimalSilvestreDAO();

        dao.criarTabela();
        dao.limparTabela();

        AnimalSilvestre animal = new AnimalSilvestre("Onça-pintada", 55.0, "Floresta Amazônica", true);
        dao.salvar(animal);

        List<AnimalSilvestre> animais = dao.listarTodos();
        assertEquals(1, animais.size());
        assertEquals("Onça-pintada", animais.get(0).getNome());

        animal.setPesoMedio(60.0);
        animal.setBioma("Cerrado");
        animal.setEmRiscoExtincao(false);
        dao.atualizar(animal);

        AnimalSilvestre atualizado = dao.buscarPorNome("Onça-pintada");
        assertEquals(60.0, atualizado.getPesoMedio());
        assertEquals("Cerrado", atualizado.getBioma());
        assertFalse(atualizado.isEmRiscoExtincao());

        dao.excluir("Onça-pintada");
        assertTrue(dao.listarTodos().isEmpty());
    }
}
