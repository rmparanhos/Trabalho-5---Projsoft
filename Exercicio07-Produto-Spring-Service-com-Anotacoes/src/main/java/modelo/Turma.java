package modelo;

import util.Util;

import javax.persistence.*;
import java.util.Calendar;

@Entity
@Table(name="turma")

public class Turma
{
    private Long id;
    private String nome;
    private String cod;
    private Calendar dataInicio;

    // ********* Construtores *********

    public Turma()    {
    }

    public Turma(String nome, String cod, Calendar dataInicio) {
        this.nome = nome;
        this.cod = cod;
        this.dataInicio = dataInicio;
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

    @Column(name="COD")
    public String getCodigo() {
        return cod;
    }

    @Column(name="DATA_INICIO")
    @Temporal(TemporalType.DATE)
    public Calendar getDataInicio() {
        return dataInicio;
    }

    @Transient
    public String getDataInicioMasc() {
        return Util.calendarToStr(dataInicio);
    }


    // ********* Métodos do Tipo Set *********

    @SuppressWarnings("unused")
    private void setId(Long id)
    {	this.id = id;
    }

    public void setNome(String nome)
    {	this.nome = nome;
    }

    public void setCod(String cod)
    {	this.cod = cod;
    }

    public void setDataInicio(Calendar dataInicio)
    {	this.dataInicio = dataInicio;
    }


}

