package dao;

import excecao.ObjetoNaoEncontradoException;
import modelo.Aluno;

import java.util.List;

public interface AlunoDAO
{	
	public long inclui(Aluno umProduto);

	public void altera(Aluno umProduto)
		throws ObjetoNaoEncontradoException; 
	
	public void exclui(long id) 
		throws ObjetoNaoEncontradoException; 
	
	public Aluno recuperaUmAluno(long numero)
		throws ObjetoNaoEncontradoException; 
	
	public List<Aluno> recuperaAlunos();
}