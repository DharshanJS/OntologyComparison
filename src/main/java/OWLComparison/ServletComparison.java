package OWLComparison;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

import org.apache.jena.base.Sys;
import org.apache.jena.query.Query;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;
import org.apache.jena.rdf.model.Literal;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.RDFNode;
import org.apache.jena.util.FileManager;


@WebServlet("/ServletComparison")
public class ServletComparison extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        FileManager.get().addLocatorClassLoader(ServletComparison.class.getClassLoader());
        Model modelFile = FileManager.get().loadModel
                ("/home/dharshan/IdeaProjects/SampleWebProject/src/main/java/OWLComparison/CVDetails.rdf");

//        Model modelFile2 = FileManager.get().loadModel
//                ("C:\\Users\\admin\\IdeaProjects\\SampleWebProject\\src\\main\\java\\OWLComparison\\JobAdvt.rdf");


        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        try {
            String jobCatname = request.getParameter("jobCat");
            String jobID = request.getParameter("JobID");

            String query =
                    "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>" +
                            "PREFIX owl: <http://www.w3.org/2002/07/owl#> " +
                            "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#> " +
                            "PREFIX ns0: <http://www.Jobsurveillance.com/CV#> " +
                            "SELECT (str(?object) as ?label) " +
                            "(str(?object2) as ?label2) " +
                            "(str (?phoneNumobj) as ?label3) " +
                            "(str (?degreeobj) as ?label4) " +
                            "(str (?jobcatObj) as ?label5) " +
                            "(str (?candidateSkill) as ?label8) " +
                            "(str (?candidateExp) as ?label9) " +
                            "(str (?qualificationType) as ?label10) " +
                            "(str (?jobSkill) as ?label7) WHERE { " +
                            " ?person ns0:Full_Name ?object ." +
                            " ?person ns0:Email_Id ?object2 ." +
                            " ?person ns0:Phone_Number ?phoneNumobj ." +
                            " ?person ns0:Degree ?degreeobj ." +
                            " ?person ns0:Job_Category ?jobcatObj ." +
                            " ?person ns0:Skills ?candidateSkill ." +
                            " ?person ns0:YearsofExperience ?candidateExp ." +
                            " ?person ns0:Qualification_Type ?qualificationType ." +
                            " ?job ns0:JobID  \"" + jobID + "\"." +
                            " ?job ns0:Required_Skills ?jobSkill ." +
                            " FILTER regex(?jobSkill, ?candidateSkill)" +
//                            " filter (?jobSkill = ?candidateSkill)" +
                            "}";


            Query queryString = QueryFactory.create(query);
            QueryExecution qexec = QueryExecutionFactory.create(query, modelFile);
            ResultSet rs = qexec.execSelect();
            out.println("<html><head><title> Search Results </title>");
            out.println("<link rel='stylesheet' type='text/css' href='https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css' />");
            out.println("<LINK REL=\"StyleSheet\" HREF=\"/style.css\" TYPE=\"text/css\"> ");
            out.println("</head><body>");

            String navbar = "<nav class=\"navbar navbar-inverse\">\n" +
                    "        <div class=\"container-fluid\">\n" +
                    "            <div class=\"navbar-header\">\n" +
                    "                <a class=\"navbar-brand\" href=\"/index.jsp\">Job Candidate Comparison</a>\n" +
                    "            </div>\n" +
                    "            <ul class=\"nav navbar-nav\">\n" +
                    "                <li class=\"active\"><a href=\"#\">Home</a></li>\n" +
                    "                <li><a href=\"#\">Job References</a></li>\n" +
                    "                <li class=\"dropdown\"><a class=\"dropdown-toggle\" data-toggle=\"dropdown\" href=\"#\"> Candidate Results<span class=\"caret\"></span></a>\n" +
                    "                    <ul class=\"dropdown-menu\">\n" +
                    "                        <li><a href=\"#\">Page 1-1</a></li>\n" +
                    "                        <li><a href=\"#\">Page 1-2</a></li>\n" +
                    "                        <li><a href=\"#\">Page 1-3</a></li>\n" +
                    "                    </ul>\n" +
                    "                </li>\n" +
                    "            </ul>\n" +
                    "            <ul class=\"nav navbar-nav navbar-right\">\n" +
                    "                <li><a href=\"#\"><span class=\"glyphicon glyphicon-user\"></span> Sign Up</a></li>\n" +
                    "                <li><a href=\"#\"><span class=\"glyphicon glyphicon-log-in\"></span> Login</a></li>\n" +
                    "            </ul>\n" +
                    "        </div>\n" +
                    "    </nav>";
            out.println(navbar);


            String str = "<table id='table'><thead class=\"thead-dark\"><tr>" +
                    "<th scope=\"col\">Candidate</th>" +
                    "<th scope=\"col\">Email Address</th>" +
                    "<th scope=\"col\">Contact Number</th>" +
                    "<th scope=\"col\">Skills</th>" +
                    "<th scope=\"col\">Score Points</th>" +
                    "</tr> </thead> <tbody>";


            QuerySolution sol = null;
            RDFNode object5 = null;
            RDFNode object6 = null;

            while (rs.hasNext()) {
                sol = rs.nextSolution();
                RDFNode object = sol.get("label");
                RDFNode object2 = sol.get("label2");
                RDFNode object3 = sol.get("label3");
                RDFNode object4 = sol.get("label4");
                object5 = sol.get("label8");
                object6 = sol.getLiteral("label9");
                int exp =((Literal) object6).getInt(); //error highlight this line
                RDFNode object7 = sol.get("label10");

                int scorePointQualification=0;
                int scorePointExperience=0;

                if (object7.toString().equals("Degree")){
                    scorePointQualification = 5;
                }
                else if (object7.toString().equals("Diploma")){
                    scorePointQualification = 3;
                }

                if (exp >= 0 && exp <= 3){
                    scorePointExperience = 4;
                }
                else if(exp >= 4 && exp <= 7){
                    scorePointExperience = 5;
                }

                int subTotalPoints = scorePointExperience + scorePointQualification;

                str += "<tr><td> " + object + " </td>" +
                        "<td> " + object2 + " </td>" +
                        "<td> " + object3 + " </td>" +
                        "<td> " + object5 + " </td>" +
                        "<td title ='Highest Qualification: "+ object4 +" Experience: "+ object6 +"'> " + subTotalPoints + " </td>" +
                        "</tr>";

            }

            str += "</tbody></table>";
            out.println(str);

            out.println("<form> " +
                    "<input type=\"button\" " +
                    "value=\"View Statistics\" " +
                    "onclick=\"window.location.href='http://localhost:8080/PictorialRepresentation.jsp'\" />" +
                    "</form>");

            out.println("<script> " +
                    "var table = document.getElementById('table');" +
                    "for (var i = 0; i <= table.rows.length; i++){" +
                        "table.rows[i].onclick = function () {" +
                            "document.getElementById(\"experience\").value = \"" + object6 + "\";" +
                        "};" +
                    "}" +
                    "</script>");

            String printOtherDetails =
                    "<div class='Other-Details'>" +
                            "Years of Experience: <input type='text' name='experience' id='experience' readonly/><br><br>";

            out.println(printOtherDetails);

            out.println("</body></html>");

        }
        catch (Exception e){
            System.err.println(e);
        } finally {
            out.close();
        }
    }
}
