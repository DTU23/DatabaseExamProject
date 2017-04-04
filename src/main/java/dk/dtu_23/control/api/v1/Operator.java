package dk.dtu_23.control.api.v1;

import dk.dtu_23.model.data.dao.MySQLOperatorDAO;
import dk.dtu_23.model.data.interfaces.DALException;
import dk.dtu_23.model.OperatorDTO;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;


@Path("api/v1/operator")
public class Operator {
    private List<OperatorDTO> list = new ArrayList<>();
    private MySQLOperatorDAO DAO = new MySQLOperatorDAO();
    public Operator()
    {
        try {
            list.addAll(this.DAO.getOperatorList());
        } catch (DALException e){
            e.printStackTrace();
        }
    }

    @GET
    @Path("{opr_id}")
    @Produces(MediaType.APPLICATION_JSON)
    public OperatorDTO getOperator(@PathParam("opr_id") String oprId) {
        return list.get(Integer.parseInt(oprId));
    }

    @GET
    @Path("")
    @Produces(MediaType.APPLICATION_JSON)
    public List<OperatorDTO> getOperator() {
        return list;
    }
}
