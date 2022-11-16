package edu.cmu.webgen.project;

import org.jetbrains.annotations.NotNull;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public class CompositeArticle implements Articles{
    List<Articles> articles;

    public void addArticle(Articles article){
        this.articles.add(article);
    }

    public void removeArticle(Articles article){
        articles.remove(article);
    }

    @Override
    public int compareTo(@NotNull Articles that) {
        return 0;
    }

    @Override
    public String getId() {
        return null;
    }

    @Override
    public @NotNull LocalDateTime getLastUpdate() {
        LocalDateTime res = null;
        for(Articles a: articles){
           LocalDateTime localDateTime =  a.getLastUpdate();
           if(localDateTime.compareTo(res) > 0){
               res = localDateTime;
           }
        }
        return res;
    }

    @Override
    public @NotNull LocalDateTime getCreated() {
        LocalDateTime res = null;
        for(Articles a: articles){
            LocalDateTime localDateTime =  a.getCreated();
            if(localDateTime.compareTo(res) > 0){
                res = localDateTime;
            }
        }
        return res;
    }

    @Override
    public @NotNull String getTitle() {

        return null;
    }

    @Override
    public void addMetadata(Metadata m) {

    }

    @Override
    public Metadata getMetadata() {
        return null;
    }

    @Override
    public void addContent(AbstractContent newcontent) {

    }

    @Override
    public LocalDateTime getPublishedDate() {
        return null;
    }

    @Override
    public List<AbstractContent> getContent() {
        return null;
    }
}
