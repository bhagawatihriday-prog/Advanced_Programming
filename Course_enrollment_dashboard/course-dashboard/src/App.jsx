import React, { useState, useMemo } from "react";
import "./App.css";

const COURSE_POOL = ["DSA", "Math", "AI", "ML", "DBMS", "OS"];

function generateStudents(n = 1000) {
  const students = [];

  for (let i = 1; i <= n; i++) {
    const gpa = (Math.random() * 4 + 6).toFixed(2);

    const shuffled = [...COURSE_POOL].sort(() => 0.5 - Math.random());
    const courseCount = Math.floor(Math.random() * 3) + 1;

    students.push({
      id: i,
      name: `Student ${i}`,
      gpa: parseFloat(gpa),
      courses: shuffled.slice(0, courseCount)
    });
  }

  return students.sort((a, b) => b.gpa - a.gpa);
}

export default function App() {
  const [students, setStudents] = useState(() => generateStudents(1000));

  const [name, setName] = useState("");
  const [gpa, setGpa] = useState("");
  const [coursesInput, setCoursesInput] = useState("");
  const [filter, setFilter] = useState("");

  const addStudent = () => {
    if (!name || !gpa) return;

    const courses = coursesInput
      .split(",")
      .map(c => c.trim())
      .filter(Boolean);

    const newStudent = {
      id: Date.now(),
      name,
      gpa: parseFloat(gpa),
      courses
    };

    setStudents(prev =>
      [...prev, newStudent].sort((a, b) => b.gpa - a.gpa)
    );

    setName("");
    setGpa("");
    setCoursesInput("");
  };

  const removeStudent = id => {
    setStudents(prev => prev.filter(s => s.id !== id));
  };

  const filteredStudents = useMemo(() => {
    if (!filter.trim()) return students;

    return students.filter(s =>
      s.courses.some(c =>
        c.toLowerCase().includes(filter.toLowerCase())
      )
    );
  }, [students, filter]);

  const uniqueCourses = useMemo(() => {
    const set = new Set();
    students.forEach(s => s.courses.forEach(c => set.add(c)));
    return Array.from(set);
  }, [students]);

  return (
    <div className="page">

      <div className="container">

        <h1 className="title">🎓 Course Enrollment Dashboard</h1>

        <div className="form-row">
          <input
            placeholder="Name"
            value={name}
            onChange={e => setName(e.target.value)}
          />

          <input
            placeholder="GPA"
            type="number"
            value={gpa}
            onChange={e => setGpa(e.target.value)}
          />

          <input
            placeholder="Courses (DSA,Math,AI)"
            value={coursesInput}
            onChange={e => setCoursesInput(e.target.value)}
          />

          <button onClick={addStudent}>Add</button>
        </div>

        

        <h2>All Unique Courses</h2>

        <div className="tags">
          {uniqueCourses.map(c => (
            <span key={c} className="tag">
              {c}
            </span>
          ))}
        </div>

        <input
          className="filter"
          placeholder="🔍 Filter by course"
          value={filter}
          onChange={e => setFilter(e.target.value)}
        />

        <h2>Students (Sorted by GPA)</h2>

        <div className="students">

          {filteredStudents.map(student => (
            <div key={student.id} className="student-card">

              <div>
                <strong>{student.name}</strong>
                <div>GPA: {student.gpa}</div>

                <div className="tags">
                  {student.courses.map(c => (
                    <span key={c} className="tag">
                      {c}
                    </span>
                  ))}
                </div>
              </div>

              <button
                className="remove"
                onClick={() => removeStudent(student.id)}
                
              >
                Remove
              </button>

            </div>
          ))}

        </div>

        

      </div>

    </div>
  );
}