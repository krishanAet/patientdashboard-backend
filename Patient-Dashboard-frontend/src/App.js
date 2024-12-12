import React from "react";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import Dashboard from "./dashboard/Dashboard";
import AddPatients from "./patients/AddPatients";
import EditPatient from "./patients/EditPatient";

const App = () => {
  return (
    <Router>
      <Routes>
        <Route 
          path="/" 
          element={<Dashboard />} 
        />
        <Route
          path="/add-patient"
          element={<AddPatients />}
        />
        <Route
          path="/edit-patient/:id"
          element={<EditPatient />}
        />
      </Routes>
    </Router>
  );
};

export default App;
