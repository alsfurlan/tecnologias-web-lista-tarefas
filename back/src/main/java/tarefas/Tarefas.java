/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tarefas;

import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("tarefas")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class Tarefas {
    
    static Integer contador = 0;
    static List<Tarefa> tarefas = new ArrayList<>();
    
    @GET
    @Path("/helloworld")
    @Produces(MediaType.TEXT_PLAIN)
    public String helloWorld() {
        return "Hello world!";
    }
    
    @GET
    public List<Tarefa> getTarefas() {
        return tarefas;
    }
    
    @GET
    @Path("{id}")
    public Tarefa getTarefa(@PathParam("id") Integer id) {
        for(Tarefa t : tarefas) {
            if(t.getId().equals(id)) {
                return t;
            }
        }
        return null;
    }
    
    @POST
    public void adicionar(Tarefa tarefa) {
        tarefa.setId(++contador);
        tarefa.setConcluida(false);
        tarefas.add(tarefa);
    }
    
    @PUT
    @Path("{id}")
    public void atualizar(@PathParam("id") Integer id, Tarefa tarefaAtualizada) {
        Tarefa tarefa = getTarefa(id);
        if(tarefa != null) {
            tarefa.setDescricao(tarefaAtualizada.getDescricao());
            tarefa.setConcluida(tarefaAtualizada.getConcluida());
        }
    }
    
    @DELETE
    @Path("{id}")
    public void excluir(@PathParam("id") Integer id) {
        Tarefa tarefa = getTarefa(id);
        if(tarefa != null) {
            tarefas.remove(tarefa);
        }
    }
    
    @POST
    @Path("{id}/concluir")
    public void concluir(@PathParam("id") Integer id) {
        Tarefa tarefa = getTarefa(id);
        tarefa.setConcluida(!tarefa.getConcluida());
    }
}
 