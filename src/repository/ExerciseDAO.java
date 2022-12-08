package repository;

import model.Exercise;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ExerciseDAO {

    public static Exercise String;
    static List<Exercise> exercises = new ArrayList<>();

    public void save(Exercise exercise) {
        ExerciseRepository exerciseRepository = new ExerciseRepository();
        try {
            if (exercise.getId() != null) {
                exerciseRepository.update(exercise);
            } else {
                exercise.setId(exerciseRepository.nextId());
                exerciseRepository.insert(exercise);
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        exercises.add(exercise);
    }

    public void remove(Exercise exercise) throws Exception {
        ExerciseRepository exerciseRepository = new ExerciseRepository();
        exerciseRepository.delete(exercise);
    }

    public List<Exercise> searchAll() throws SQLException, ClassNotFoundException {
        ExerciseRepository exerciseRepository = new ExerciseRepository();
        try{
            exercises = exerciseRepository.search();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        return exercises;
    }

    public List<Exercise> searchWithName(String name) {
        ExerciseRepository exerciseRepository = new ExerciseRepository();
        List<Exercise> filtredExercises = new ArrayList<>();

        try{
            exercises = exerciseRepository.searchByName(name);

            for (Exercise exercise : exercises){
                filtredExercises.add(exercise);
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return filtredExercises;
    }

    public Exercise searchById(Integer id) {
        ExerciseRepository exerciseRepository = new ExerciseRepository();
        Exercise exercise = new Exercise();
        try{
            exercises = exerciseRepository.searchById(id);

            for (Exercise auxExercise : exercises){
                if (auxExercise.getId().equals(id)) {
                    exercise = auxExercise;
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return exercise;
    }

    public Object[] searchAllOnlyWithName() throws SQLException, ClassNotFoundException {
        ExerciseRepository exerciseRepository = new ExerciseRepository();
        ArrayList<String> names = new ArrayList<>();
        try{
            exercises = exerciseRepository.search();

            for (Exercise exercise : exercises) {
                names.add(exercise.getName());
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        return names.toArray();
    }

    public Object[] searchAllWithIdOnName() {
        ExerciseRepository exerciseRepository = new ExerciseRepository();
        List<String> exercisesName = new ArrayList<>();

        try{
            exercises = exerciseRepository.search();

            for (Exercise exercise : exercises) {
                exercisesName.add(exercise.getId() + " - " + exercise.getName());
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        return exercisesName.toArray();
    }
}
