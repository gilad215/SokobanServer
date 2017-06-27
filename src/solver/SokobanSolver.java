package solver;


import model.data.Level;
import strips.strips.*;

import java.util.LinkedList;
import java.util.List;

public class SokobanSolver {

    private SokobanPlannable plannable=null;
    private Planner planner=new strips();

    public SokobanSolver(Level lvl)
    {
        plannable=new SokobanPlannable(lvl);
    }

    public LinkedList<String> solve()
    {
        if(plannable!=null)
        {
            List<Action> stripsActions = planner.plan(plannable);
            LinkedList<String> outputActions = new LinkedList<>();

            for (Action act : stripsActions) {
                if (act.getAct() != null) outputActions.add(act.getAct());
            }
            return outputActions;
        }
        return null;
    }


    //    public static void main(String[] args) throws IOException {
//
//        MySokobanLoader loader= new MySokobanLoader(args[0]);
//        loader.load();
//
//        SokobanPlannable plannable= new SokobanPlannable(loader.getLvl());
//
//        SolutionSaver saver=new SolutionSaver();
//
//        Planner planner=new strips();
//        List<Action> stripsActions=planner.plan(plannable);
//        LinkedList<String> outputActions=new LinkedList<>();
//
//        for (Action act:stripsActions) {
//            if(act.getAct()!=null) outputActions.add(act.getAct());
//        }
//        System.out.println(outputActions);
//        saver.save(outputActions,args[1]);
//}
}
