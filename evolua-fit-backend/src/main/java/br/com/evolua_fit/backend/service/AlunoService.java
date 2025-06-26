package br.com.evolua_fit.backend.service;

import br.com.evolua_fit.backend.dto.AlunoDto;
import br.com.evolua_fit.backend.exception.ResourceNotFoundException;
import br.com.evolua_fit.backend.model.Aluno;
import br.com.evolua_fit.backend.repository.AlunoRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlunoService {

    @Autowired
    private AlunoRepository alunoRepository;

    public Aluno criarAluno(AlunoDto alunoDto) {
        Aluno aluno = new Aluno();
        BeanUtils.copyProperties(alunoDto, aluno);
        return alunoRepository.save(aluno);
    }

    public List<Aluno> listarTodosAlunos() {
        return alunoRepository.findAll();
    }

    public Aluno buscarAlunoPorId(Long id) {
        return alunoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Aluno não encontrado com o id: " + id));
    }

    public Aluno atualizarAluno(Long id, AlunoDto alunoDto) {
        Aluno alunoExistente = buscarAlunoPorId(id);
        BeanUtils.copyProperties(alunoDto, alunoExistente, "id");
        return alunoRepository.save(alunoExistente);
    }

    public void desativarAluno(Long id) {
        Aluno aluno = buscarAlunoPorId(id);
        aluno.setAtivo(false);
        alunoRepository.save(aluno);
    }
}
