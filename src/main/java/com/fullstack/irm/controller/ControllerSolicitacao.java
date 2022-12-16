package com.fullstack.irm.controller;

import com.fullstack.irm.entity.Cliente;
import com.fullstack.irm.entity.Exame;
import com.fullstack.irm.entity.Solicitacao;
import com.fullstack.irm.entity.TipoExame;
import com.fullstack.irm.repository.ClienteRepository;
import com.fullstack.irm.repository.ExameRepository;
import com.fullstack.irm.repository.SolicitacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;

@Controller
public class ControllerSolicitacao {

    @Autowired
    private SolicitacaoRepository solitacoesRep;

    @Autowired
    private ClienteRepository clientesRep;

    @Autowired
    private ExameRepository examesRep;

    //*** Método GET que disponibiliza o formulário para permitir CREATE e UPDATE de Solicitações de Exame ***//
    @GetMapping("/solicitacao")
    public String formularioCreateSolicitacao(Solicitacao solicitacao, Model model) {

        List<Cliente> clientes = clientesRep.findAll();

        List<Exame> listaexames = examesRep.findAll();

        model.addAttribute("clientes", clientes);

        model.addAttribute("listaexames", listaexames);

        return "create_solicitacao"; //create_solicitacao.html
    }
    //******************************************************************************************************//

    //*** Método POST que adiciona a solicitação ao Banco de Dados através do formulário acima ***//
    @PostMapping("/postSolicitacao")
    public String postSolicitacao(@Valid Solicitacao solicitacao, BindingResult result, @RequestParam("cpf") String cpf) {

        if (result.hasFieldErrors()) {
            return "redirect:/solicitacao";
            //CORRIGIR: Informar que existe algum erro no formulário!
        }
        
        Cliente cliente = clientesRep.findByCpf(cpf);
        solicitacao.setCliente(cliente);

        solitacoesRep.save(solicitacao);
        return "redirect:/inicio";
        //CORRIGIR: Informar que a solicitação foi cadastrada com sucesso.
    }
    //*******************************************************************************************//

}
