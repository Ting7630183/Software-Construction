package edu.cmu.webgen.project;

import edu.cmu.webgen.WebGen;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.*;

public class LeafArticle implements Articles{
    protected @NotNull
    final String directoryName;
    protected @NotNull
    final Set<Topic> topics = new HashSet<>();
    //    final List<Event> innerEvents;
    final List<AbstractContent> content;
    private final LocalDateTime lastUpdate;
    private final LocalDateTime created;
    protected @Nullable String id = null;
    protected @NotNull Metadata metadata = new Metadata();

    public LeafArticle(List<AbstractContent> content, @NotNull String directoryName,
                   @NotNull LocalDateTime created, @NotNull LocalDateTime lastUpdate) {
        this.content = content;
        this.lastUpdate = lastUpdate;
        this.created = created;
        this.directoryName = directoryName;
    }

    @Override
    public int compareTo(@NotNull Articles that) {
        return this.getTitle().compareTo(that.getTitle());
    }

    @Override
    public String getId() {
        if (this.id == null)
            this.id = WebGen.genId(getTitle());
        return this.id;
    }

    @Override
    public @NotNull LocalDateTime getLastUpdate() {
        return this.lastUpdate;
    }

    @Override
    public @NotNull LocalDateTime getCreated() {
        return this.created;
    }

    @Override
    public @NotNull String getTitle() {
        if (this.metadata.has("title"))
            return this.metadata.get("title");
        for (AbstractContent n : this.content)
            if (n.hasTitle())
                return Objects.requireNonNull(n.getTitle());
        return this.directoryName;
    }

    @Override
    public void addMetadata(Metadata m) {
        this.metadata = this.metadata.concat(m);
        this.topics.addAll(Topic.from(m));
    }

    @Override
    public Metadata getMetadata() {
        return this.metadata;
    }

    @Override
    public List<AbstractContent> getContent() {
        return this.content;
    }

    @Override
    public void addContent(AbstractContent newcontent) {
        this.content.add(newcontent);
    }

    @Override
    public LocalDateTime getPublishedDate() {
        if (this.metadata.has("date")) {
            try {
                return WebGen.parseDate(this.metadata.get("date"));
            } catch (ParseException e) {
                System.err.println(e.getMessage());
            }
        }
        return getLastUpdate();
    }
}
