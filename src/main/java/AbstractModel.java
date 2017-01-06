/**
 * Created by michalik on 06.01.17
 */
class AbstractModel {
    private String author;
    private String university;
    private String contact;
    private String title;
    private String subtitle;
    private String abstractBody;

    public String getAuthor() {
        return author;
    }

    public String getUniversity() {
        return university;
    }

    public String getContact() {
        return contact;
    }

    public String getTitle() {
        return title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public String getAbstractBody() {
        return abstractBody;
    }

    @Override
    public String toString() {
        return "AbstractModel{" +
                "author='" + author + '\'' +
                ", university='" + university + '\'' +
                ", contact='" + contact + '\'' +
                ", title='" + title + '\'' +
                ", subtitle='" + subtitle + '\'' +
                ", abstractBody='" + abstractBody + '\'' +
                '}';
    }
}
