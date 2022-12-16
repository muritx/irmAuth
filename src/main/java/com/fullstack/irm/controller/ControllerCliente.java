package com.fullstack.irm.controller;

import com.fullstack.irm.dto.UserDto;
import com.fullstack.irm.entity.Cliente;
import com.fullstack.irm.entity.Exame;
import com.fullstack.irm.entity.Role;
import com.fullstack.irm.entity.Solicitacao;
import com.fullstack.irm.entity.User;
import com.fullstack.irm.repository.ClienteRepository;
import com.fullstack.irm.repository.ExameRepository;
import com.fullstack.irm.repository.RoleRepository;
import com.fullstack.irm.repository.SolicitacaoRepository;
import com.fullstack.irm.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

import java.util.Arrays;
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
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    private PasswordEncoder passwordEncoder;

    public ControllerCliente(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

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

        User user = new User();
        user.setFullName(cliente.getNome());
        user.setUserName(cliente.getCpf());
        user.setPassword(passwordEncoder.encode(cliente.getTelefone()));

        Role role = roleRepository.findByName("PACIENTE");
        if(role == null){
            role = checkRoleExist();
        }
        user.setRoles(Arrays.asList(role));
        userRepository.save(user);

        cliente.setUltimaalteracao(new Date());

        clienteRepository.save(cliente);

        return "redirect:/historicoclientes";
        //CORRIGIR: Informar que o cliente foi cadastrado com sucesso.
    }
    //*****************************************************************************************//

    //*** Método GET que disponibiliza o formulário para permitir o UPDATE de Cliente ***//
    @GetMapping("cliente/{id}")
    public String formularioUpdateCliente(Model model, @PathVariable("id") int id) {

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

        User user = userRepository.findByUserName(cliente.getCpf());
        user.setPassword(passwordEncoder.encode(cliente.getTelefone()));

        userRepository.save(user);

        cliente.setUltimaalteracao(new Date());

        clienteRepository.save(cliente);
        return "redirect:/historicoclientes";
        //CORRIGIR: Informar que o cliente foi cadastrado com sucesso.
    }
    //****************************************************************************************//

    //*** Método GET que permite o DELETE do cliente diretamento no Banco de Dados ***//
    @GetMapping("deletecliente/{id}")
    public String deleteCliente(@PathVariable("id") int id, Model model) {

        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid person Id:" + id));

        User user = userRepository.findByUserName(cliente.getCpf());
        user.setRoles(null);
        userRepository.delete(user);

        clienteRepository.delete(cliente);

        return "redirect:/historicoclientes";
    }

    //********************************************************************************//

    //*** Método GET que exibe todos os clientes cadastrados ***//
    @GetMapping("historicoclientes")
    public String listAllClientes(Model model) {
        List<Cliente> clientes = clienteRepository.findAll();

        model.addAttribute("clientes", clientes);

        return "historico_cliente"; //historico_cliente.html
    }
    //**********************************************************//

    //*** Método GET que acessa os dados do cliente selecionado ***//
    @GetMapping("historicoclientes/{id}")
    public String verInformacoes(Model model, @PathVariable(name = "id") int id) {

        List<Cliente> clientes = clienteRepository.findAll();
        List<Exame> listaexames = exameRepository.findAll();
        
        model.addAttribute("clientes", clientes);
        model.addAttribute("listaexames", listaexames);

        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid person Id:" + id));

        List<Solicitacao> solicitacoes = solicitacaoRepository.findSolicitacaoByCliente(cliente);
        model.addAttribute("solicitacoes", solicitacoes);

        model.addAttribute("cliente", cliente);
        return "pagina_cliente";

    }

    //*************************************************************//

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

    private Role checkRoleExist() {
        Role role = new Role();
        role.setName("PACIENTE");
        return roleRepository.save(role);
    }

    //Busca nome cliente

    @GetMapping("getNameByCpf/{cpf}")
    public Cliente getNameByCpf(Model model, @PathVariable(name = "cpf") String cpf) {

        Cliente cliente = clienteRepository.findByCpf(cpf);
                //.findById(id)
                //.orElseThrow(() -> new IllegalArgumentException("Invalid person Id:" + id));

        //model.addAttribute("cliente", cliente);
        return cliente;
        //pagina_cliente.html
    }
}
