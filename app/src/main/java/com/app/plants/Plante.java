package com.app.plants;

public class Plante {
    private int id;
    private String nom;
    private String nomScientifique;
    private String famille;
    private String origine;
    private String type;
    private String genre;
    private String toxicite;
    private String usage;
    private byte[] image;

    public Plante(String nom, String nomScientifique, String famille, String origine, String type, String genre , String toxicite, String  usage, byte[] image, int id) {
        this.nom = nom;
        this.nomScientifique = nomScientifique;
        this.famille = famille;
        this.origine = origine;
        this.type = type;
        this.genre = genre;
        this.toxicite = toxicite;
        this.usage = usage;

        this.image = image;
        this.id = id;
    }





    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setNomScientifique(String nomScientifique) {
        this.nomScientifique = nomScientifique;
    }
    public String getNomScientifique() {
        return nomScientifique;
    }

    public String getFamille() {
        return famille;
    }

    public void setFamille(String famille) {
        this.famille = famille;
    }


    public String getOrigine() {
        return origine;
    }

    public void setOrigine(String origine) {
        this.origine = origine;
    }
    public String getType() {
        return type;
    }



    public void setType(String type) {
        this.type = type;
    }
    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }
    public String getToxicite() {
        return toxicite;
    }

    public void setToxicite(String toxicite) {
        this.toxicite = toxicite;
    }
    public String getUsage() {
        return usage;
    }

    public void setUsage(String usage) {
        this.usage = usage;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
}