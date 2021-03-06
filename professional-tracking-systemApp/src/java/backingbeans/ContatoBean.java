/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backingbeans;

import dao.ClienteDAO;
import dao.ContatoDAO;
import dao.ProfissionalDAO;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import model.Cliente;
import model.Contato;
import model.Profissional;

/**
 *
 * @author Bruno
 */
@ManagedBean
@SessionScoped
public class ContatoBean {

    private int idProfissional = 1;
    private int idCliente = 1;
    private Contato contato = new Contato();
    private ContatoDAO contatoDAO = new ContatoDAO();
    private Cliente cliente = new Cliente();
    private Profissional profissional = new Profissional();
    private ProfissionalDAO profissionalDAO = new ProfissionalDAO();
    private ClienteDAO clienteDAO = new ClienteDAO();
    private List<Contato> mensagensProfissional;
    private List<Contato> mensagensCliente;
    
    public ContatoBean() {
        this.mensagensProfissional = contatoDAO.listarMensagensProfissional(idProfissional);
        this.mensagensCliente = contatoDAO.listarMensagensCliente(idCliente);
    }

    public String enviarMensagem() {

        this.contatoDAO = new ContatoDAO();
        this.cliente = clienteDAO.carregar(idCliente);
        this.contato.setCliente(cliente);
        this.profissional = profissionalDAO.carregar(idProfissional);
        this.contato.setProfissional(profissional);
        this.contatoDAO.incluir(contato);
        FacesContext contexto = FacesContext.getCurrentInstance();
        contexto.addMessage(null, new FacesMessage("Mensagem Enviada!", ""));
        this.contato = new Contato();
        this.profissional = new Profissional();
        this.cliente = new Cliente();
        return null;
    }

    public List<Contato> buscaMensagensProfissional() {
        mensagensProfissional = contatoDAO.listarMensagensProfissional(idProfissional);
        return mensagensProfissional;
    }

    public List<Contato> buscaMensagensCliente() {
        mensagensCliente = contatoDAO.listarMensagensCliente(idCliente);
        return mensagensCliente;
    }

    public int getIdProfissional() {
        return idProfissional;
    }

    public void setIdProfissional(int idProfissional) {
        this.idProfissional = idProfissional;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public Contato getContato() {
        return contato;
    }

    public void setContato(Contato contato) {
        this.contato = contato;
    }

    public ContatoDAO getContatoDAO() {
        return contatoDAO;
    }

    public void setContatoDAO(ContatoDAO contatoDAO) {
        this.contatoDAO = contatoDAO;
    }

    public ProfissionalDAO getProfissionalDAO() {
        return profissionalDAO;
    }

    public void setProfissionalDAO(ProfissionalDAO profissionalDAO) {
        this.profissionalDAO = profissionalDAO;
    }

    public ClienteDAO getClienteDAO() {
        return clienteDAO;
    }

    public void setClienteDAO(ClienteDAO clienteDAO) {
        this.clienteDAO = clienteDAO;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Profissional getProfissional() {
        return profissional;
    }

    public void setProfissional(Profissional profissional) {
        this.profissional = profissional;
    }

    public List<Contato> getMensagensProfissional() {
        return mensagensProfissional;
    }

    public void setMensagensProfissional(List<Contato> mensagensProfissional) {
        this.mensagensProfissional = mensagensProfissional;
    }

    public List<Contato> getMensagensCliente() {
        return mensagensCliente;
    }

    public void setMensagensCliente(List<Contato> mensagensCliente) {
        this.mensagensCliente = mensagensCliente;
    }

    public String iniciarContato(int idProfissional) {
        this.idProfissional = idProfissional;

        return "FormularioContato?faces-redirect=true";
    }
    
    public String responderCliente() {        
        return "FormularioRespostaProfissional?faces-redirect=true";
    }
    

    public String returnIndex() {
        return "BuscaProfissionalPorCategoria?faces-redirect=true";
    }

}
