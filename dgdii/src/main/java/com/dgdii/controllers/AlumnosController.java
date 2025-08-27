package com.dgdii.controllers;

import com.dgdii.models.Alumnos;
import com.dgdii.controllers.util.JsfUtil;
import com.dgdii.controllers.util.PaginationHelper;
import com.dgdii.ejb.AlumnosFacade;
import com.dgdii.models.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;

@Named("alumnosController")
@ViewScoped
public class AlumnosController implements Serializable {
    
    @EJB private AlumnosFacade alumnoEJB;
    
    private Alumnos current;
    private AlumnosMaterias alumnoMateria;
    private Personas persona;
    private Materias materia; 
    private Paises pais;
    private Estados estado;
    private Municipios municipio;
    private Colonias colonia;
    private List<AlumnosMaterias> alumnosMateriasList = null;
    private Set<Integer> materias = null;
    private PaginationHelper pagination;
    private int selectedItemIndex;
    private DataModel items = null;
    
    @PostConstruct
    public void init() {
        if (persona == null) {
            persona = new Personas();
        }
        if (materia == null) {
            materia = new Materias();
        }
        
        if (alumnoMateria == null) {
            alumnoMateria = new AlumnosMaterias();
        }
        if (alumnosMateriasList == null) {
            alumnosMateriasList = new ArrayList<>();
        }
        if (materias == null) {
            materias = new HashSet<>();
        }
        System.out.println("Se inicia PostConstructor");
    }
    
    public AlumnosController() {
    }

    public void guardarAlumno() {

        // valida que haya al menos una materia
        if (alumnosMateriasList == null || alumnosMateriasList.isEmpty()) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            "Falta seleccionar materias",
                            "Debes agregar al menos una materia."));
            // marca la validación como fallida para que PrimeFaces no “avance”
            FacesContext.getCurrentInstance().validationFailed();
            return; // permanecer en la misma vista
        }
        //Ai ya hay materias, entonces ya hacemos todo esto

        System.out.println("Loading...");
        System.out.println("Alumno: ");
        System.out.println("Nombre: " + this.persona.getNombre());
        System.out.println("Apellido Paterno: " + this.persona.getApaterno());
        System.out.println("Apellido Materno : " + this.persona.getAmaterno());
        System.out.println("Sexo: " + this.persona.getSexo());
        System.out.println("Fecha Nacimiento: " + this.persona.getFechaNacimiento());
        System.out.println("Colonia: " + this.colonia.getColonia());
        System.out.println("Municipio: " + this.municipio.getMunicipio());
        System.out.println("Estado: " + this.estado.getEstado());
        System.out.println("Pais: " + this.pais.getPais());
        System.out.println("Matricula: " + this.current.getMatricula());
        System.out.println("Fecha Ingreso: " + this.current.getFechaIngreso());
        System.out.println("Materia: " + this.materia.getMateria());
        System.out.println("Profesor: " + this.materia.getIdProfesor().getIdPersona().getNombre());

        persona.setIdColonia(colonia);
        current.setIdPersona(persona);
        current.setAlumnosMateriasList(alumnosMateriasList);

        this.create();
    }
    
    /**
     * 
     * @param materia Es la materia que capturamos al darle click en el front de agregar materia
     */
    public void agregarMateriaList(Materias materia) {
        Integer mid = materia.getIdMateria(); //Aqui solo guardamos el Id de la materia que capturamos para comparar en el set

        if (materia != null && materias.contains(mid)) { //Aqui solo verificamos si el id de la materia ya existe en el set

            System.out.println("Ya existe en la lista");
            JsfUtil.addErrorMessage("Materia repetida");
            return; //retornamos para que se salga del metodo y no siga con el codigo
            
        }

        //Si el id de la materia no estaba en el set, entonces agregamos a la lista
        AlumnosMaterias am = new AlumnosMaterias();
        am.setIdAlumno(current);    // puede ser null en UI, no pasa nada
        am.setIdMateria(materia);
        alumnosMateriasList.add(am);

        //Y ademas agregamos el id al set, para las verificaciones
        if (mid != null) {
            materias.add(mid);
        }
        JsfUtil.addSuccessMessage("Materia agregada a la tira de materias");
    }
    
    /**
     * 
     * @param am es el objeto de tipo AlumnosMaterias, donde capturamos la materia que quiere eliminar
     * la atrapamos desde el frontend en la tabla de materias seleccionadas
     */
    public void eliminarMateriaList(AlumnosMaterias am){  
        Integer mid = am.getIdMateria().getIdMateria();
        alumnosMateriasList.remove(am);
        materias.remove(mid);
        System.out.println("Materia eliminada: "+am.getIdMateria().getMateria());
        JsfUtil.addSuccessMessage("Materia eliminada de la tira de materias");
        
    }
    
    public PaginationHelper getPagination() {
        if (pagination == null) {
            pagination = new PaginationHelper(10) {

                @Override
                public int getItemsCount() {
                    return getFacade().count();
                }

                @Override
                public DataModel createPageDataModel() {
                    return new ListDataModel(getFacade().findRange(new int[]{getPageFirstItem(), getPageFirstItem() + getPageSize()}));
                }
            };
        }
        return pagination;
    }

    public String prepareList() {
        recreateModel();
        return "List";
    }

    public String prepareView() {
        current = (Alumnos) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "View";
    }

    public String prepareCreate() {
        current = new Alumnos();
        persona = new Personas();
        materia = new Materias();
        alumnoMateria = new AlumnosMaterias();
        materias = new HashSet<>();
        alumnosMateriasList = new ArrayList<>();
        pais = new Paises();
        estado = new Estados();
        municipio = new Municipios();
        colonia = new Colonias();
        selectedItemIndex = -1;
        return "Create";
    }

    public String create() {
        try {
            getFacade().create(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("AlumnosCreated"));
            return prepareCreate();
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String prepareEdit() {
        current = (Alumnos) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "Edit";
    }

    public String update() {
        try {
            getFacade().edit(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("AlumnosUpdated"));
            return "View";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String destroy() {
        current = (Alumnos) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        performDestroy();
        recreatePagination();
        recreateModel();
        return "List";
    }

    public String destroyAndView() {
        performDestroy();
        recreateModel();
        updateCurrentItem();
        if (selectedItemIndex >= 0) {
            return "View";
        } else {
            // all items were removed - go back to list
            recreateModel();
            return "List";
        }
    }

    private void performDestroy() {
        try {
            getFacade().remove(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("AlumnosDeleted"));
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
        }
    }

    private void updateCurrentItem() {
        int count = getFacade().count();
        if (selectedItemIndex >= count) {
            // selected index cannot be bigger than number of items:
            selectedItemIndex = count - 1;
            // go to previous page if last page disappeared:
            if (pagination.getPageFirstItem() >= count) {
                pagination.previousPage();
            }
        }
        if (selectedItemIndex >= 0) {
            current = getFacade().findRange(new int[]{selectedItemIndex, selectedItemIndex + 1}).get(0);
        }
    }

    public DataModel getItems() {
        if (items == null) {
            items = getPagination().createPageDataModel();
        }
        return items;
    }

    private void recreateModel() {
        items = null;
    }

    private void recreatePagination() {
        pagination = null;
    }

    public String next() {
        getPagination().nextPage();
        recreateModel();
        return "List";
    }

    public String previous() {
        getPagination().previousPage();
        recreateModel();
        return "List";
    }

    public SelectItem[] getItemsAvailableSelectMany() {
        return JsfUtil.getSelectItems(alumnoEJB.findAll(), false);
    }

    public SelectItem[] getItemsAvailableSelectOne() {
        return JsfUtil.getSelectItems(alumnoEJB.findAll(), true);
    }

    public Alumnos getAlumnos(java.lang.Integer id) {
        return alumnoEJB.find(id);
    }

    @FacesConverter(forClass = Alumnos.class)
    public static class AlumnosControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            AlumnosController controller = (AlumnosController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "alumnosController");
            return controller.getAlumnos(getKey(value));
        }

        java.lang.Integer getKey(String value) {
            java.lang.Integer key;
            key = Integer.valueOf(value);
            return key;
        }

        String getStringKey(java.lang.Integer value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Alumnos) {
                Alumnos o = (Alumnos) object;
                return getStringKey(o.getIdAlumno());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + Alumnos.class.getName());
            }
        }

    }
        
    public Personas getPersona() { return persona; }            
    public void setPersona(Personas persona) { this.persona = persona; }
    public Paises getPais() { return pais; }
    public void setPais(Paises pais) { this.pais = pais; }
    public Estados getEstado() { return estado; }
    public void setEstado(Estados estado) { this.estado = estado; }
    public Municipios getMunicipio() { return municipio; }
    public void setMunicipio(Municipios municipio) { this.municipio = municipio; }
    public Colonias getColonia() { return colonia; }
    public void setColonia(Colonias colonia) { this.colonia = colonia; }
    private AlumnosFacade getFacade() { return alumnoEJB; }
    public Materias getMateria() { return materia; }
    public void setMateria(Materias materia) { this.materia = materia; }
    public List<AlumnosMaterias> getAlumnosMateriasList() { return alumnosMateriasList; }
    public void setAlumnosMateriasList(List<AlumnosMaterias> alumnosMateriasList) { this.alumnosMateriasList = alumnosMateriasList; }
    
    public Alumnos getSelected() {
        if (current == null) {
            current = new Alumnos();
            selectedItemIndex = -1;
        }
        return current;
    } 
}
