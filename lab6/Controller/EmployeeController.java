package com.example.lab6.Controller;

import com.example.lab6.ApiResponce.ApiResponce;
import com.example.lab6.Model.Employee;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/Employee")
public class EmployeeController {
    ArrayList<Employee> employees=new ArrayList<>();

    @GetMapping("/get")
   public ResponseEntity getEmployee(){
       return ResponseEntity.status(200).body(employees);
   }


   @PostMapping("/add")
   public ResponseEntity addEmployee(@RequestBody @Valid Employee employee, Errors errors){
       if(errors.hasErrors()){
           String message=errors.getFieldError().getDefaultMessage();

           return ResponseEntity.status(400).body(message);
       }
       employees.add(employee);
       return ResponseEntity.status(200).body(new ApiResponce("employee added"));
   }

   @PutMapping("/update/{index}")
   public ResponseEntity updateEmployee(@PathVariable int index,@RequestBody @Valid Employee employee,Errors errors){
       if(errors.hasErrors()){
           String message=errors.getFieldError().getDefaultMessage();

           return ResponseEntity.status(400).body(message);
       }
               employees.set(index,employee );
       return ResponseEntity.status(200).body(new ApiResponce("emplyee updated"));


   }

    @DeleteMapping("/delete/{id}")
   public ResponseEntity deleteEmployee(@PathVariable String id){
           for(Employee e:employees){
               if(e.getId().equals(id)){
                   employees.remove(e);
                   return ResponseEntity.status(200).body(new ApiResponce("Employee deleted"));
               }}
            return ResponseEntity.status(400).body(new ApiResponce("Employee not found "));
   }

   @GetMapping("/search/{position}")
    public ResponseEntity searchpos(@PathVariable String position){
        ArrayList<Employee> search=new ArrayList<>();
        for( Employee e : employees){
            if(e.getPosition().equalsIgnoreCase(position)){
                search.add(e);

            }
        }
       if (search.isEmpty())
       return ResponseEntity.status(400).body("no employee found");
       else
       return ResponseEntity.status(200).body(search);
    }


    @GetMapping("age/{min}/{max}")
    public ResponseEntity getAges(@PathVariable int min,@PathVariable int max){
        ArrayList<Employee> ages=new ArrayList<>();
        for(Employee e:employees){
            if(e.getAge() >= min && e.getAge() <= max){
                ages.add(e);
            }
        }
        if(min<25)
            return ResponseEntity.status(400).body("no employee under 25");
        else
            return ResponseEntity.status(200).body(ages);
    }

    @PutMapping("/annual/{id}")
    public ResponseEntity annual(@PathVariable String id){
        for(Employee e:employees){
            if(e.getId().equals(id)){
                if(e.getOnleav().equals(false)){
                    if(e.getAnnualleave()>=1){
                        e.setOnleav(true);
                        if(e.getAnnualleave()!=0)
                        e.setAnnualleave(e.getAnnualleave()-1);
                        else return ResponseEntity.status(400).body("you dont have annual leave");
                    }
                }
            }
        }return ResponseEntity.status(200).body("done");
    }

    @GetMapping("searchann")
    public ResponseEntity searchannual(){
        ArrayList<Employee> annual=new ArrayList<>();
        for( Employee e : employees){
            if(e.getAnnualleave()==0){
                annual.add(e);

            }
        }
        if (annual.isEmpty())
            return ResponseEntity.status(400).body("no employee found");
        else
            return ResponseEntity.status(200).body(annual);
    }

    @PutMapping("/promote/{id}/{id2}")
    public ResponseEntity promote (@PathVariable String id,@PathVariable String id2){
        for(Employee e:employees){
            if(e.getId().equals(id)){
                if(e.getPosition().equalsIgnoreCase("supervisor")){
                    for(Employee e2:employees){
                    if(e2.getAge()>=30){
                        if(e2.getOnleav().equals(false)){
                            e2.setPosition("supervisor");
                            return ResponseEntity.status(200).body("done");}
                        }
                    }
                }
            }
        }return ResponseEntity.status(400).body("not change");
    }
}
