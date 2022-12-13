package WebPkg;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.DiskFileUpload;
import org.apache.commons.fileupload.FileItem;

/**
 * Servlet implementation class FileUpload
 */
@WebServlet("/FileUpload")
public class FileUpload extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FileUpload() {
        super();
        // TODO Auto-generated constructor stub
    }
    private void configCORS(HttpServletResponse response){
    	response.addHeader("Access-Control-Allow-Origin", "*");
    	response.addHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, PUT, DELETE, HEAD");
    	response.addHeader("Access-Control-Allow-Headers", "X-PINGOTHER, Origin, X-Requested-With, Content-Type, Accept");
    	response.addHeader("Access-Control-Max-Age", "1728000");
    	}
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Post received!");
		if (org.apache.commons.fileupload.FileUpload.isMultipartContent(request)) {
			DiskFileUpload upload = new DiskFileUpload();
			upload.setSizeMax(50*1024*1024); //50Mb
			try {
			List items = upload.parseRequest(request);
			Iterator it = items.iterator();
			while (it.hasNext()) {
				FileItem fitem = (FileItem) it.next();
				String fileName=fitem.getName();
				if (!fitem.isFormField()) {
					File f= new File(fileName);
					FileOutputStream fo= new FileOutputStream(f);
					DataOutputStream dados= new DataOutputStream(fo);
					byte[] b=fitem.get();
					dados.write(b,0,(int)fitem.getSize());
					dados.close();
					fo.close();
					System.out.println(f.getAbsolutePath());
				}else {
					String fieldName = fitem.getFieldName();
					String conteudo = fitem.getString();
					System.out.println(fieldName+": " + conteudo);
				}
			}
			}catch(Exception e) {
				e.printStackTrace();
			}
			}
	}

}
