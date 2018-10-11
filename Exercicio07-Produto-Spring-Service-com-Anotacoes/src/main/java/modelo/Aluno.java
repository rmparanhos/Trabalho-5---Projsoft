package modelo;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import util.Util;

@Entity
@Table(name="aluno")

public class Aluno
{
    private Long id;
    private String nome;
    private String curso;
    private Calendar dataNascimento;
    private Calendar dataCadastro;

    // ********* Construtores *********

    public Aluno()    {
    }

    public Aluno(String nome, String curso, Calendar dataNascimento,Calendar dataCadastro) {
        this.nome = nome;
        this.curso = curso;
        this.dataNascimento = dataNascimento;
        this.dataCadastro = dataCadastro;
    }

    // ********* Métodos do Tipo Get *********

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="ID")
    public Long getId() {
        return id;
    }

    @Column(name="NOME")
    public String getNome() {
        return nome;
    }

    @Column(name="CURSO")
    public String getCurso() {
        return curso;
    }

    @Column(name="DATA_NASCIMENTO")
    @Temporal(TemporalType.DATE)
    public Calendar getDataNascimento() {
        return dataNascimento;
    }

    @Transient
    public String getDataNascimentoMasc() {
        return Util.calendarToStr(dataNascimento);
    }

    @Column(name="DATA_CADASTRO")
    @Temporal(TemporalType.DATE)
    public Calendar getDataCadastro() {
        return dataCadastro;
    }

    @Transient
    public String getDataCadastroMasc() {
        return Util.calendarToStr(dataCadastro);
    }

    // ********* Métodos do Tipo Set *********

    @SuppressWarnings("unused")
    private void setId(Long id)
    {	this.id = id;
    }

    public void setNome(String nome)
    {	this.nome = nome;
    }

    public void setCurso(String curso)
    {	this.curso = curso;
    }

    public void setDataNascimento(Calendar dataNascimento)
    {	this.dataNascimento = dataNascimento;
    }

    public void setDataCadastro(Calendar dataCadastro)
    {	this.dataCadastro = dataCadastro;
    }
}

