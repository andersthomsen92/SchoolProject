package assignment3;


import org.junit.Before;

import java.util.ArrayList;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import static org.junit.Assert.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TeamTest {
    private Team team;

    @Before
    public void setUp() {
        team = new Team();
    }

    @Test
    public void testAddTeamMember() {
        TeamMember member = new TeamMember(0, "Kurt Kortzen", "Project Manager");
        team.addTeamMember(member);
        int expected = 1;
        int actual = team.getAllMembers().size();
        assertEquals(expected, actual);
    }
    
    @Test
    public void testAddTeamMembers() {
        TeamMember member = new TeamMember(0, "Kurt Kortzen", "Project Manager");
        team.addTeamMember(member);
        TeamMember member2 = new TeamMember(1, "Gurli Gunnersson", "Developer");
        team.addTeamMember(member2);
        int expected = 2;
        assertEquals(expected, team.getAllMembers().size());
    }
    
    @Test
    public void testRemoveTeamMember() {
        TeamMember member = new TeamMember(0, "Kurt Kortzen", "Project Manager");
        team.getAllMembers().add(member);
        TeamMember member2 = new TeamMember(1, "Gurli Gunnersson", "Developer");
        team.getAllMembers().add(member2);
        TeamMember member3 = new TeamMember(2, "Alf Nielsen", "Project Manager");
        team.getAllMembers().add(member3);
        assertTrue(team.removeTeamMember(member2));
        int expected = 2;
        int actual = team.getAllMembers().size();
        assertEquals(expected, actual);
    }
    
    @Test
    public void testGetNextId() {
        TeamMember member = new TeamMember(0, "Kurt Kortzen", "Project Manager");
        team.getAllMembers().add(member);
        TeamMember member2 = new TeamMember(1, "Gurli Gunnersson", "Developer");
        team.getAllMembers().add(member2);
        TeamMember member3 = new TeamMember(2, "Alf Nielsen", "Project Manager");
        team.getAllMembers().add(member3);
        int expected = 3;
        int actual = team.getNextId();
        assertEquals(expected, actual);
    }
    
    @Test
    public void testGetNextIdListWithHoles() {
        TeamMember member = new TeamMember(0, "Kurt Kortzen", "Project Manager");
        team.getAllMembers().add(member);
        TeamMember member3 = new TeamMember(2, "Alf Nielsen", "Project Manager");
        team.getAllMembers().add(member3);
        int expected = 1;
        int actual = team.getNextId();
        assertEquals(expected, actual);
    }
    
    @Test
    public void testGetNextIdFullList() {
        TeamMember member = new TeamMember(0, "Kurt Kortzen", "Project Manager");
        team.getAllMembers().add(member);
        TeamMember member2 = new TeamMember(1, "Gurli Gunnersson", "Developer");
        team.getAllMembers().add(member2);
        TeamMember member3 = new TeamMember(2, "Alf Nielsen", "Project Manager");
        team.getAllMembers().add(member3);
        team.getAllMembers().trimToSize();
        int expected = 3;
        int actual = team.getNextId();
        assertEquals(expected, actual);
    }
    
    @Test
    public void testRetrieveTeamMembersByRole() {
        TeamMember member = new TeamMember(0, "Kurt Kortzen", "Project Manager");
        team.getAllMembers().add(member);
        TeamMember member2 = new TeamMember(1, "Gurli Gunnersson", "Developer");
        team.getAllMembers().add(member2);
        TeamMember member3 = new TeamMember(2, "Alf Nielsen", "Project Manager");
        team.getAllMembers().add(member3);
        ArrayList<TeamMember> members = team.retriveMembersByRole("Project Manager");  
        assertNotNull(members);
        int expected = 2;
        int actual = members.size();
        assertEquals(expected, actual);
    }
    
    //opgave 3e
    @Test
    public void testSortByNameAsc(){
        ArrayList<TeamMember> members = new ArrayList<>();
        members.add(new TeamMember(0, "Kurt Kortzen", "Project Manager"));
        members.add(new TeamMember(1, "Gurli Gunnersson", "Developer"));
        members.add(new TeamMember(2, "Alf Nielsen", "Janitor"));
        
        team.addTeamMembers(members);
        team.sortByNameAsc();
        ArrayList<TeamMember> membersSorted = team.getAllMembers();
        if(membersSorted.size()!=members.size())
            fail();
        
        for (int i = 1; i < membersSorted.size(); i++) {
            TeamMember member = membersSorted.get(i);
            TeamMember prev = membersSorted.get(i-1);
            if(member.getName().compareTo(prev.getName()) < 0){
                fail();
            }
        }
    }

    //opgave 3f
    @Test
    public void testSortByRoleDesc(){
        ArrayList<TeamMember> members = new ArrayList<>();
        members.add(new TeamMember(0, "Kurt Kortzen", "Project Manager"));
        members.add(new TeamMember(1, "Gurli Gunnersson", "Developer"));
        members.add(new TeamMember(2, "Alf Nielsen", "Janitor"));
        
        team.addTeamMembers(members);
        team.sortByRoleDesc();
        ArrayList<TeamMember> sortedMembers = team.getAllMembers();
        
        if(sortedMembers.size()!=members.size())
            fail();
        
        for (int i = 1; i < sortedMembers.size(); i++) {
            TeamMember member = sortedMembers.get(i);
            TeamMember prev = sortedMembers.get(i-1);
            if(member.getRole().compareTo(prev.getRole()) > 0){
                fail();
            }
        }
    }
}
