package com.dgdii.controllers;

import com.dgdii.models.Alumnos;
import com.dgdii.controllers.util.JsfUtil;
import com.dgdii.controllers.util.PaginationHelper;
import com.dgdii.ejb.AlumnosFacade;
import com.dgdii.models.Colonias;
import com.dgdii.models.Estados;
import com.dgdii.models.Municipios;
import com.dgdii.models.Paises;
import com.dgdii.models.Personas;
import java.io.Serializable;
import java.util.ResourceBundle;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
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
    
    private Personas persona;
    private Paises pais;
    private Estados estado;
    private Municipios municipio;
    private Colonias colonia;
    
    private PaginationHelper pagination;
    private int selectedItemIndex;
    private DataModel items = null;
    
    
    public AlumnosController() {
        System.out.println("Se inicia constructor");
    }

    @PostConstruct
    public void init(){
        persona = new Personas();
        System.out.println("Se inicia constructor");
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

        // Entity References
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
    
    public Alumnos getSelected() {
        if (current == null) {
            current = new Alumnos();
            selectedItemIndex = -1;
        }
        return current;
    }

    
    
    
}
