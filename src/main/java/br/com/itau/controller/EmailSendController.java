package br.com.itau.controller;

import br.com.itau.models.Contrato;
import br.com.itau.repositories.ContratoRepository;
import br.com.itau.service.EmailSendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
public class EmailSendController {

    @Autowired
    private EmailSendService emailSendService;
    @Autowired
    private ContratoRepository contratoRepository;

    @PostMapping(path="/envia/{id}")
    public ResponseEntity<HttpStatus> emailSendService( @PathVariable Long id) throws Exception {
        Optional<Contrato> contratoOptional = contratoRepository.findById(id);
        String resposta = emailSendService.sendEmail(contratoOptional);
        if (resposta.equals("200")){
            return ResponseEntity.ok(HttpStatus.OK);
        }
        return ResponseEntity.badRequest().body(HttpStatus.BAD_REQUEST);
    }

}
