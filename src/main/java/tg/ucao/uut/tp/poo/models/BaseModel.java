/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tg.ucao.uut.tp.poo.models;

import tg.ucao.uut.tp.poo.dao.Transient;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe de base pour tous les modèles.
 * Gère l'ID et la récupération dynamique des champs pour le DAO.
 */
public abstract class BaseModel {

    protected Long id;

    public BaseModel() {
        // Constructeur par défaut
    }

    public BaseModel(Long id) {
        this.id = id;
    }

    /**
     * Indique si l'objet est nouveau (pas encore persistant en BD)
     */
    public boolean isNew() {
        return id == null;
    }

    // =======================
    // GETTERS / SETTERS
    // =======================
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    // =======================
    // Champs dynamiques pour DAO
    // =======================
    public Object[] getPersistFieldsVal() {
        return getFieldsVal(false);
    }

    public Object[] getFieldsVal() {
        return getFieldsVal(true);
    }

    public Object[] getFieldsVal(boolean takeTransients) {
        List<Object> values = new ArrayList<>();
        for (Field field : getFields(takeTransients)) {
            field.setAccessible(true);
            try {
                values.add(field.get(this));
            } catch (IllegalAccessException e) {
                throw new RuntimeException("Erreur accès champ : " + field.getName(), e);
            } finally {
                field.setAccessible(false);
            }
        }
        return values.toArray();
    }

    public Field[] getPersistFields() {
        return getFields(false);
    }

    public Field[] getFields() {
        return getFields(true);
    }

    public Field[] getFields(boolean takeTransients) {
        List<Field> fieldList = new ArrayList<>();
        Class<?> clazz = this.getClass();

        // Récupère uniquement les champs déclarés dans la classe fille
        for (Field field : clazz.getDeclaredFields()) {
            if ("id".equals(field.getName())) continue;        // Ignore l'ID
            if (!takeTransients && field.isAnnotationPresent(Transient.class)) continue;
            fieldList.add(field);
        }

        return fieldList.toArray(new Field[0]);
    }

    // =======================
    // Méthode obligatoire pour TableView / DAO
    // =======================
    public abstract Object[] toTableRow();
}
