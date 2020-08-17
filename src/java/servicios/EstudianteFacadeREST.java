/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicios;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import modelo.Estudiante;

/**
 *
 * @author jgasd
 */
@Stateless
@Path("modelo.estudiante")
public class EstudianteFacadeREST extends AbstractFacade<Estudiante> {

    @PersistenceContext(unitName = "apisandmysqlPU")
    private EntityManager em;

    public EstudianteFacadeREST() {
        super(Estudiante.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(Estudiante entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") String id, Estudiante entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") String id) {
        super.remove(super.find(id));
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Estudiante find(@PathParam("id") String id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Estudiante> findAll() {
        return super.findAll();
    }
    
    
    /////////////EJEMPLOS de CLASE primeer PERIODO//////////////////
    //primer ejemplo buscando todos losestudiantes o todos los datos
         
    @POST
    @Path("busest")
   @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON}) 
    public List<Estudiante> Busestud(){
    return super.findAll();
    }
    
    //eviando solo un mensage
    @POST
    @Path ("mensage")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public String Mensage(){
        return "Hola Mundo";
    }
    
    ///recibiendo un dato de Postman
    @GET
    @Path ("HolaNombre")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_JSON})
    public String HolaNombre(@QueryParam("nombre")String nomb){
        
      return "Bievenido "+ nomb;  
    }
    //Para realizando una suma de postman
    @GET
    @Path ("suma")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_JSON})
    public String resultado(@QueryParam("num1") int nu1,@QueryParam("num2")int nu2){
        int suma= nu1+nu2;
        return "La suma es: "+ suma;
    }
    //Para realizando una multiplicion de postman
    @POST
    @Path ("mult")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_JSON})
    public String mut(@FormParam("num1") int un1,@FormParam("num2")int nu2){
     int multi=un1*nu2 ;
     return "la mutliplicacion es: "+ multi;
     
    }
    
    /// Para realizar una busqueda del numeros ingresados cual es el mayor con Postman usando "GET"
    @GET
    @Path ("numayor")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_JSON})
    public String com(@QueryParam("num1") int nu1,@QueryParam("num2")int nu2){
     
       if(nu1>nu2){
            return "Este el mayor: "+ nu1+" del "+ nu2;
        }else{
           return "Este el mayor: "+ nu2+" del "+ nu1;
          }
          }
     /// Para realizar una busqueda del numeros ingresados cual es el mayor con Postman usando "POST"
    @POST
    @Path ("numayorpost")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_JSON})
    public String comp(@FormParam("num1") int nu1,@FormParam("num2")int nu2){
        if(nu1>nu2){
            return "Este el mayor: "+ nu1+" del "+ nu2;
        }else{
           return "Este el mayor: "+ nu2+" del "+ nu1;
        }
       
    }
    ///Para ingresar datos auna tabla
    
    @POST
    @Path ("creardatos")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public String crear(@FormParam("cedula")String cedula,@FormParam("nombre")String nombre,@FormParam("apellido")
    String apellido,@FormParam("edad")String edad, @FormParam("sexo")String sexo,@FormParam("direccion") String direccion,
    @FormParam("telefono")String telefono,@FormParam("correo") String correo){
        if(cedula.length()==10){
        Estudiante ob = new Estudiante(cedula, nombre,  apellido,  edad,  sexo, direccion, telefono,  correo);            
       super.create(ob);
       
        return "Objeto se inserto correctamente";
        }else{
            return "No se guardo revice bien su cedula";
        }
        }
    
      ///Para buscar datos auna tabla por item
    @POST
    @Path("busporitem")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_JSON})
    public List <Estudiante> buscar(@FormParam("sexo")String sexo){
      TypedQuery consulta = getEntityManager().createQuery("SELECT e FROM Estudiante e WHERE e.sexo = :sexo",Estudiante.class);
      consulta.setParameter("sexo", sexo);
      return consulta.getResultList();
        
    }
      ///Para atualizar datos de un registro en una tabla
    @POST
    @Path ("editarcapos")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_JSON})
    public String editarcrear(@FormParam("cedula")String cedula,@FormParam("nombre")String nombre,@FormParam("apellido")
    String apellido,@FormParam("edad")String edad, @FormParam("sexo")String sexo,@FormParam("direccion") String direccion,
    @FormParam("telefono")String telefono,@FormParam("correo") String correo){
     
        Estudiante ob= super.find(cedula);
        ob.setNombre(nombre);
        ob.setApellido(apellido);
        ob.setEdad(edad);
        ob.setSexo(sexo);
        ob.setDireccion(direccion);
        ob.setTelefono(telefono);
        ob.setCorreo(correo);
        super.edit(ob);
        return "Se actualizo con exito";
        
    }
    
      ///Para Eliminar un registro en una tabla
    @POST
    @Path ("eliminar")
     @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_JSON})
    public String borrar(@FormParam("cedula") String cedula){
    Estudiante ob = super.find(cedula);
    if(ob== null){
        return "No hay estudiante con esta cedula: "+ cedula;
        
    }else{
         super.remove(ob);
    return "se elimino el estudiante con la cedula: "+cedula;
    }
       
    }
        
       
    ////////////////////////////////////////////////////////////////         

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Estudiante> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public int countREST() {
        return (super.count());
    }
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
