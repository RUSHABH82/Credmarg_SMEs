import {Outlet} from "react-router-dom";
import Mailbox from "../mail/Mailbox.jsx";

export const Home = () => {
  return (
    <div>
      <Mailbox />
      <Outlet />
    </div>
  );
};
