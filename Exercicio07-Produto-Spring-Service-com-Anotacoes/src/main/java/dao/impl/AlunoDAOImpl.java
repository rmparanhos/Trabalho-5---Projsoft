package dao.impl;

import dao.AlunoDAO;
import excecao.ObjetoNaoEncontradoException;
import modelo.Aluno;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class AlunoDAOImpl implements AlunoDAO
{	
	@PersistenceContext
	private EntityManager em;

    public long inclui(Aluno umAluno)
	{	
		em.persist(umAluno);
		return umAluno.getId();
	}

	public void altera(Aluno umAluno)
		throws ObjetoNaoEncontradoException 
	{	
		Aluno aluno = em.find(Aluno.class, umAluno.getId(), LockModeType.PESSIMISTIC_WRITE);
		
		if(aluno == null)
		{	throw new ObjetoNaoEncontradoException();
		}

		em.merge(umAluno);
	}

	public void exclui(long id) 
		throws ObjetoNaoEncontradoException 
	{	
		Aluno aluno = em.find(Aluno.class, id, LockModeType.PESSIMISTIC_WRITE);
		
		if(aluno == null)
		{	throw new ObjetoNaoEncontradoException();
		}
		
		em.remove(aluno);
	}

	public Aluno recuperaUmAluno(long id)
		throws ObjetoNaoEncontradoException 
	{	
		Aluno umAluno = (Aluno)em.find(Aluno.class, new Long(id));
			
		if (umAluno == null)
		{	throw new ObjetoNaoEncontradoException();
		}

		return umAluno;
	}

	@SuppressWarnings("unchecked")
	public List<Aluno> recuperaAlunos()
	{	
		List<Aluno> alunos = em.createQuery("select p from Aluno p " +
					                            "order by p.id asc")
			                       .getResultList();

		return alunos;
	}
}