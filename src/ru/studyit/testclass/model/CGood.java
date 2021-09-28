package ru.studyit.testclass.model;

import java.util.UUID;

public class CGood {
    private UUID id;
    public UUID getId()
    {
        return id;
    }

    private String name;
    public String getName()
    {
        return name;
    }
    public void setName(String name)
    {
        this.name = name;
    }

    public CGood(UUID id, String name)
    {
        this.id = id;
        setName(name);
    }
}
