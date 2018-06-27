package com.pmcc.soft.task.simpleflows.manager;

import com.pmcc.soft.task.base.SnakerBase;
import org.snaker.engine.SnakerEngine;
import org.snaker.engine.access.QueryFilter;
import org.snaker.engine.entity.Order;
import org.snaker.engine.entity.Task;
import org.snaker.engine.helper.StreamHelper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class SimpleflowsManager extends SnakerBase {

    public void initFlows(String workflow) {
        if (workflow.equals("1")) {
            processId = snakerEngine.process().deploy(StreamHelper.getStreamFromClasspath("workFlows/approval.snaker"));
        }
    }

    /**
     * 发起评审
     *
     * @param headId   计划Id
     * @param operator 处理人
     */
    public void startTask(String headId, String operator, String workflow) {
        //初始化流程定义
        initFlows(workflow);
        Map<String, Object> variables = new HashMap<String, Object>();
        variables.put("owner", operator);
        variables.put("headId", headId);
        Order order = snakerEngine.startInstanceById(processId, operator, variables);
        List<Task> tasks = snakerEngine.query().getActiveTasks(new QueryFilter().setOrderId(order.getId()));
        if (tasks != null && tasks.size() > 0) {
            String taskId = tasks.get(0).getId();
            variables.put("msg", "发起审批");
            snakerEngine.executeTask(taskId, operator, variables);
        }
    }

    /**
     * 执行评审结果
     *
     * @param taskId 任务Id
     * @param res  1同意2拒绝
     */
    public String executeTask(String taskId, String operator, String res) {
        Map<String, Object> variables = new HashMap<String, Object>();
        variables.put("res", res);
        Task task = snakerEngine.query().getTask(taskId);
        try {
            snakerEngine.executeTask(taskId, operator, variables);
        } catch (Exception a) {
            a.printStackTrace();
            System.out.println(a.getMessage());
        }

        return task.getTaskName();
    }

    /**
     * 获取待办任务
     *
     * @param operator 处理人
     */
    public List<Map<String, String>> workTasks(String operator) {
        List<Map<String, String>> workTasks = new ArrayList<Map<String, String>>();
        Map<String, String> variableMap = null;
        List<Task> tasks = snakerEngine.query().getActiveTasks(new QueryFilter().setOperator(operator));
        for (Task task : tasks) {
            Order order = snakerEngine.query().getOrder(task.getOrderId());
            variableMap = new HashMap<String, String>();
            variableMap.put("taskId", task.getId());
            variableMap.put("headId", (String) order.getVariableMap().get("headId"));
            workTasks.add(variableMap);
        }
        return workTasks;
    }


    /**
     * 终止任务
     *
     * @param orderId
     */
    public void terminateTask(String orderId) {
        snakerEngine.order().terminate(orderId, SnakerEngine.ADMIN);
    }
}
