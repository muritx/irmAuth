package com.fullstack.irm.controller;

import com.fullstack.irm.entity.Cliente;
import com.fullstack.irm.entity.Exame;
import com.fullstack.irm.entity.Solicitacao;
import com.fullstack.irm.repository.ClienteRepository;
import com.fullstack.irm.repository.ExameRepository;
import com.fullstack.irm.repository.SolicitacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

@Controller
public class ControllerCliente {

    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private ExameRepository exameRepository;
    @Autowired
    private SolicitacaoRepository solicitacaoRepository;

    //*** Método GET que disponibiliza o formulário para permitir CREATE e UPDATE de Cliente ***//
    @GetMapping("/cliente")
    public String formularioCreateCliente(Cliente cliente) {

        return "create_cliente"; //create_cliente.html
    }
    //******************************************************************************************//

    //*** Método POST que adiciona o cliente ao Banco de Dados através do formulário acima ***//
    @PostMapping("/postcliente")
    public String postCliente(@Valid Cliente cliente, BindingResult result) {

        if (result.hasFieldErrors()) {
            return "redirect:/cliente";
            //CORRIGIR: Informar que existe algum erro no formulário!
        }

        cliente.setUltimaalteracao(new Date());

        clienteRepository.save(cliente);

        return "redirect:/historicoclientes";
        //CORRIGIR: Informar que o cliente foi cadastrado com sucesso.
    }
    //*****************************************************************************************//

    //*** Método GET que disponibiliza o formulário para permitir o UPDATE de Cliente ***//
    @GetMapping("cliente/{id}")
    public String formularioUpdateCliente(Model model, @PathVariable(name = "id") int id) {

        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid person Id:" + id));

        model.addAttribute("cliente", cliente);
        return "update_cliente"; //update_cliente.html
    }
    //**********************************************************************************//

    //*** Método POST que atualiza o cliente no Banco de Dados através do formulário acima ***//
    @PostMapping("updatecliente/{id}")
    public String updateCliente(@Valid Cliente cliente, BindingResult result, @PathVariable int id) {

        if (result.hasErrors()) {
            return "redirect:/cliente";
            //CORRIGIR: Informar que existe algum erro no formulário!
        }

        cliente.setUltimaalteracao(new Date());

        clienteRepository.save(cliente);
        return "redirect:/historicoclientes";
        //CORRIGIR: Informar que o cliente foi cadastrado com sucesso.
    }
    //****************************************************************************************//

    //*** Método GET que permite o DELETE do cliente diretamento no Banco de Dados ***//
    @GetMapping("deletecliente/{id}")
    public String deleteCliente(@PathVariable(name = "id") int id, Model model) {

        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid person Id:" + id));

        clienteRepository.delete(cliente);
        return "redirect:/historicoclientes";
    }
    //********************************************************************************//

    //*** Método GET que exibe todos os clientes cadastrados ***//
    @GetMapping("/historicoclientes")
    public String listAllClientes(Model model) {
        List<Cliente> clientes = clienteRepository.findAll();

        model.addAttribute("clientes", clientes);

        return "historico_cliente"; //historico_cliente.html
    }
    //**********************************************************//

    //*** Método GET que permite o READ de um cliente selecionado na página de Histórico de Clientes ***//
    @GetMapping("listcliente/{id}")
    public String listCliente(Model model, @PathVariable(name = "id") int id) {

        List<Cliente> clientes = clienteRepository.findAll();

        List<Exame> listaexames = exameRepository.findAll();

        List<Solicitacao> solicitacoes = solicitacaoRepository.findAll();

        model.addAttribute("clientes", clientes);

        model.addAttribute("listaexames", listaexames);

        model.addAttribute("solicitacoes", solicitacoes);

        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid person Id:" + id));

        model.addAttribute("cliente", cliente);
        return "pagina_cliente"; //pagina_cliente.html
    }
    //**************************************************************************************************//

}
