import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by michalik on 06.01.17
 */
public class FileUtil {
    private static final String EXTENSION = ".tex";

    public static void writeToTexFile(final AbstractModel model) throws IOException{
        String title = model.getAuthor().replaceAll("\\s+","")+EXTENSION;

        System.out.println("Writing to "+title);

        PrintWriter writer = new PrintWriter(title, "UTF-8");

        writer.println(appendHeaders());
        writer.println(beginDocument());
        writer.println(appendDocumentTitle(model));
        writer.println(afterTitle());

        writer.println(model.getAbstractBody());

        writer.println(endDocument());
        writer.close();
    }

    private static String afterTitle() {
        return "\\date {}\n" +
                " \n" +
                "\\maketitle\n" +
                " \n" +
                "\\thispagestyle{title}";
    }

    private static String appendDocumentTitle(final AbstractModel model){
        return "\\title{"+model.getTitle()+"}"+
                "\\author{"+model.getAuthor()+", \\\\ "+model.getUniversity();
    }

    private static String beginDocument() {
        return "\\begin{document}";
    }
    private static String endDocument(){
        return "\\end{document}";
    }

    private static String appendHeaders(){
        return  "\\documentclass[12pt, a4paper]{article}\n" +
                " \n" +
                "\\usepackage{polski}\n" +
                "\\usepackage[utf8]{inputenc}\n" +
                " \n" +
                "\\usepackage{amsmath}\n" +
                "\\usepackage{amssymb}\n" +
                "\\usepackage{graphicx}\n" +
                "\\usepackage{fancyhdr}\n" +
                "\\pagestyle{fancy}\n" +
                "\\fancypagestyle{title}{%\n" +
                "  %\\setlength{\\headheight}{22pt}%\n" +
                "  \\fancyhf{}% No header/footer\n" +
                "  %\\renewcommand{\\headrulewidth}{0pt}% No header rule\n" +
                "  %\\renewcommand{\\footrulewidth}{0pt}% No footer rule\n" +
                "  \\fancyfoot[R]{FIZYKA DLA MEDYKA 2017}% Page number in Centre of footer\n" +
                "  \\fancyhead[R]{FDM 2017}\n" +
                " \n" +
                "}%\n" +
                " \n" +
                "\\usepackage{graphicx}\n" +
                " \n" +
                " \n" +
                "\\usepackage[left=1 cm, right=1 cm, top=2 cm, bottom=2 cm]{geometry}\n" +
                "%\\pagestyle{fancy}\n" +
                "%\\renewcommand{\\sectionmark}[1]{\\markright{\\thesection.\\ #1}}\n" +
                "%\\renewcommand{\\headrulewidth}{0.5pt}\n" +
                "%\\fancyhf{}\n" +
                "%\\fancyhead{}\n" +
                "%\\fancyhead[L]{\\slshape{\\small FDM 2017}}";
    }
}
