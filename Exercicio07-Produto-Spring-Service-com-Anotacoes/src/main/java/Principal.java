import java.util.List;

import modelo.Produto;
import modelo.Aluno;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import servico.ProdutoAppService;
import servico.AlunoAppService;

import util.Util;
import corejava.Console;
import excecao.ProdutoNaoEncontradoException;
import excecao.AlunoNaoEncontradoException;

public class Principal
{	
	public static void main (String[] args) 
	{	
		String nome;
		//double lanceMinimo;
		String curso;
        String dataNascimento;
		String dataCadastro;
		Aluno umAluno;

        ApplicationContext fabrica = new ClassPathXmlApplicationContext("beans-jpa.xml");

		AlunoAppService alunoAppService = (AlunoAppService)fabrica
				.getBean ("alunoAppService");

		boolean continua = true;
		while (continua)
		{	System.out.println('\n' + "O que você deseja fazer?");
			System.out.println('\n' + "1. Cadastrar um aluno");
			System.out.println("2. Alterar um aluno");
			System.out.println("3. Remover um aluno");
			System.out.println("4. Listar todos os alunos");
			System.out.println("5. Sair");
						
			int opcao = Console.readInt('\n' + 
							"Digite um número entre 1 e 5:");
					
			switch (opcao)
			{	case 1:
				{
					nome = Console.readLine('\n' + 
						"Informe o nome do aluno: ");
					curso = Console.readLine(
						"Informe o curso do aluno: ");
                    dataNascimento = Console.readLine(
                            "Informe a data de nascimento do aluno: ");
					dataCadastro = Console.readLine(
						"Informe a data de cadastramento do aluno: ");
						
					umAluno = new Aluno(nome, curso, Util.strToCalendar(dataNascimento),Util.strToCalendar(dataCadastro));
					
					long numero = alunoAppService.inclui(umAluno);
					
					System.out.println('\n' + "Aluno número " +
					    numero + " incluído com sucesso!");	

					break;
				}

				case 2:
				{	int resposta = Console.readInt('\n' + 
						"Digite o número do aluno que você deseja alterar: ");
										
					try
					{	umAluno = alunoAppService.recuperaUmAluno(resposta);
					}
					catch(AlunoNaoEncontradoException e)
					{	System.out.println('\n' + e.getMessage());
						break;
					}
										
					System.out.println('\n' + 
						"Número = " + umAluno.getId() +
						"    Nome = " + umAluno.getNome() +
						"    Turma = " + umAluno.getCurso());
												
					System.out.println('\n' + "O que você deseja alterar?");
					System.out.println('\n' + "1. Nome");
					System.out.println("2. Turma");

					int opcaoAlteracao = Console.readInt('\n' + 
											"Digite um número de 1 a 2:");
					
					switch (opcaoAlteracao)
					{	case 1:
							String novoNome = Console.
										readLine("Digite o novo nome: ");
							
							umAluno.setNome(novoNome);

							try
							{	alunoAppService.altera(umAluno);

								System.out.println('\n' + 
									"Alteração de nome efetuada com sucesso!");
							}
							catch(AlunoNaoEncontradoException e)
							{	System.out.println('\n' + e.getMessage());
							}
								
							break;
					
						case 2:
							String novoCurso = Console.
									readLine("Digite o novo curso: ");
							
							umAluno.setCurso(novoCurso);

							try
							{	alunoAppService.altera(umAluno);

								System.out.println('\n' + 
									"Alteração de curso efetuada " +
									"com sucesso!");						
							}
							catch(AlunoNaoEncontradoException e)
							{	System.out.println('\n' + e.getMessage());
							}
								
							break;

						default:
							System.out.println('\n' + "Opção inválida!");
					}

					break;
				}

				case 3:
				{	int resposta = Console.readInt('\n' + 
						"Digite o número do produto que você deseja remover: ");
									
					try
					{	umAluno = alunoAppService.
										recuperaUmAluno(resposta);
					}
					catch(AlunoNaoEncontradoException e)
					{	System.out.println('\n' + e.getMessage());
						break;
					}
										
					System.out.println('\n' + 
						"Número = " + umAluno.getId() +
						"    Nome = " + umAluno.getNome());
														
					String resp = Console.readLine('\n' + 
						"Confirma a remoção do aluno?");

					if(resp.equals("s"))
					{	try
						{	alunoAppService.exclui (umAluno.getId());
							System.out.println('\n' + 
								"Aluno removido com sucesso!");
						}
						catch(AlunoNaoEncontradoException e)
						{	System.out.println('\n' + e.getMessage());
						}
					}
					else
					{	System.out.println('\n' + 
							"Aluno não removido.");
					}
					
					break;
				}

				case 4:
				{	
					List<Aluno> alunos = alunoAppService.recuperaAlunos();

					for (Aluno aluno : alunos)
					{	
						System.out.println('\n' + 
							"Id = " + aluno.getId() +
							"  Nome = " + aluno.getNome() +
							"  Turma = " + aluno.getCurso() +
                            "  Data Nascimento = " + aluno.getDataNascimentoMasc() +
							"  Data Cadastro = " + aluno.getDataCadastroMasc());
					}
					
					break;
				}

				case 5:
				{	continua = false;
					((ConfigurableApplicationContext)fabrica).close();
					break;
				}

				default:
					System.out.println('\n' + "Opção inválida!");
			}
		}
	}
}
