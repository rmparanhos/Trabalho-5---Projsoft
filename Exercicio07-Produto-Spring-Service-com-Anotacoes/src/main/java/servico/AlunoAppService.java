package servico;

import dao.AlunoDAO;
import excecao.ObjetoNaoEncontradoException;
import excecao.AlunoNaoEncontradoException;
import modelo.Aluno;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

// Funciona com ou sem essa anota��o pelo fato do bean de servi�o 
// ter sido especificado no arquivo de configura��o do Spring.

// @Service
public class AlunoAppService
{	
	@Autowired
	private AlunoDAO alunoDAO = null;
	
	@Transactional
	public long inclui(Aluno umAluno) {
		return alunoDAO.inclui(umAluno);
	}

	@Transactional
	public void altera(Aluno umAluno)
		throws AlunoNaoEncontradoException {
			try	{
				alunoDAO.altera(umAluno);
			}
			catch(ObjetoNaoEncontradoException e) {
				throw new AlunoNaoEncontradoException("Produto n�o encontrado");
			}
		
	}
	
	@Transactional
	public void exclui(long numero) 
		throws AlunoNaoEncontradoException {
		try {
			alunoDAO.exclui(numero);
		} 
		catch(ObjetoNaoEncontradoException e) {
			throw new AlunoNaoEncontradoException("Produto n�o encontrado");
		}
	}

	public Aluno recuperaUmAluno(long numero)
		throws AlunoNaoEncontradoException {
		try	{
			return alunoDAO.recuperaUmAluno(numero);
		} 
		catch(ObjetoNaoEncontradoException e) {
			throw new AlunoNaoEncontradoException("Produto n�o encontrado");
		}
	}

	public List<Aluno> recuperaAlunos() {
		return alunoDAO.recuperaAlunos();
	}
}