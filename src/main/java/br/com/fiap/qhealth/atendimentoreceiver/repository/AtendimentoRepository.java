package br.com.fiap.qhealth.atendimentoreceiver.repository;

import br.com.fiap.qhealth.atendimentoreceiver.model.Atendimento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AtendimentoRepository extends JpaRepository<Atendimento, UUID> {
}
