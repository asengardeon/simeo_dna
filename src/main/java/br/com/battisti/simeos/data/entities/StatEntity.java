package br.com.battisti.simeos.data.entities;

import javax.persistence.*;

@Entity
@Table(name= "stats", indexes = { @Index(name = "statIndex", columnList = "key, type") })
public class StatEntity {

    @Id
    private String key;

    private String type;

    @Column(columnDefinition="TEXT")
    private String content;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
