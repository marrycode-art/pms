package com.hui.project.service;

import com.hui.project.bean.Project;
import com.hui.project.bean.ProjectExample;
import com.hui.project.mapper.ProjectMapper;
import com.hui.system.bean.Employee;
import com.hui.system.bean.EmployeeExample;
import com.hui.system.mapper.EmployeeMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class ProjectServiceImpl implements ProjectService {

    @Resource
    private ProjectMapper projectMapper;
    @Resource
    private EmployeeMapper employeeMapper;

    @Override
    public List<Project> getProjectList() {
        List<Project> list = projectMapper.selectByExample(new ProjectExample());
        return list;
    }

    @Override
    public Project searchInfo(Integer pid) {
        Project project = projectMapper.selectByPrimaryKey(pid);
        return project;
    }

    @Override
    public void saveProject(Project project) {
        projectMapper.insert(project);
    }

    @Override
    public void update(Project project) {
        projectMapper.updateByPrimaryKeySelective(project);
    }

    @Override
    public boolean batchDel(Integer ids) {
        List<Integer> idList = Arrays.asList(ids);
        ProjectExample example = new ProjectExample();
        ProjectExample.Criteria criteria = example.createCriteria();
        criteria.andPidIn(idList);
        int row = projectMapper.deleteByExample(example);
        return row == idList.size();
    }

    /*@Override
    public List<Project> conditionSearch(Integer cid, String keyword, Integer orderby) {
        List<Project> list = projectMapper.conditionSearch(cid,keyword,orderby);
        return list;
    }*/
    @Override
    public List<Project> conditionSearch(Integer cid, String keyword, Integer orderby) {
        ProjectExample projectExample = new ProjectExample();
        ProjectExample.Criteria projectExampleCriteria = projectExample.createCriteria();
        //当cid为0时，模糊查询 项目名 和 项目经理名 含有"keyword"的记录
        if (cid == 0) {
            projectExampleCriteria.andPnameLike("%" + keyword + "%");

            //模糊查询employee表中字段 项目经理名 含有"keyword"的员工id
            EmployeeExample employeeExample = new EmployeeExample();
            EmployeeExample.Criteria employeeExampleCriteria = employeeExample.createCriteria();
            employeeExampleCriteria.andEnameLike("%" + keyword + "%");
            EmployeeExample.Criteria employeeExampleCriteria1 = employeeExample.createCriteria();
            employeeExampleCriteria1.andPFkEqualTo(4);
            List<Employee> employees = employeeMapper.selectByExample(employeeExample);

            List<Integer> list = new ArrayList<Integer>();
            //遍历获得员工列表，取出 员工 id 以供 以下的 子查询使用
            for(Employee employee : employees) {
                Integer eid = employee.getEid();
                list.add(eid);
            }
            /*
            *      子查询，相当于：
            *      SELECT * FROM project
            *      WHERE emp_fk IN (SELECT eid FROM employee WHERE ename LIKE '%keyword%');
            **/
            ProjectExample.Criteria projectExampleCriteria1 = projectExample.createCriteria();
            projectExampleCriteria1.andEmpFkIn(list);
        //当cid为1时，模糊查询 项目名 含"keyword"的记录
        } else if(cid == 1){
            projectExampleCriteria.andPnameLike("%" + keyword + "%");
        //此时cid为3时，模糊查询 项目经理名 含"keyword"的记录
        }else {
            EmployeeExample employeeExample = new EmployeeExample();
            EmployeeExample.Criteria employeeExampleCriteria = employeeExample.createCriteria();
            employeeExampleCriteria.andEnameLike("%" + keyword + "%");
            EmployeeExample.Criteria employeeExampleCriteria1 = employeeExample.createCriteria();
            employeeExampleCriteria1.andPFkEqualTo(4);
            List<Employee> employees = employeeMapper.selectByExample(employeeExample);

            List<Integer> list = new ArrayList<Integer>();
            for(Employee employee : employees) {
                Integer eid = employee.getEid();
                list.add(eid);
            }
            projectExampleCriteria.andEmpFkIn(list);
        }
        if (orderby == 0){
            projectExample.setOrderByClause("pid asc");
        } else if(orderby == 1) {
            projectExample.setOrderByClause("starttime asc");
        } else {
            projectExample.setOrderByClause("endtime asc");
        }

        List<Project> list = projectMapper.selectByExample(projectExample);
        return list;
    }
}
