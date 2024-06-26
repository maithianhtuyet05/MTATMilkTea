import DefaultLayout from "./components/layout/DefaultLayout";
import "react-confirm-alert/src/react-confirm-alert.css";
import { BrowserRouter as Router, Route, Switch } from "react-router-dom";
import { CircularProgress, CssBaseline } from "@material-ui/core";
import ScrollOnTop from "./common/ScrollOnTop";
import { ToastContainer } from "react-toastify";
import "./App.scss";
import PrivateRoute from "./components/PrivateRoute";
import React, { Suspense } from "react";
import CheckAddress from "./components/CheckAddress";
import ChatBox from "./components/ChatBox";
import ReturnVNPay from "./components/ReturnVNPay";

const Home = React.lazy(() => import("./components/Home"));
const Milktea = React.lazy(() => import("./components/Milktea"));
const SignIn = React.lazy(() => import("./components/SignIn"));
const SignUp = React.lazy(() => import("./components/SignUp"));
const Dessert = React.lazy(() => import("./components/Dessert"));
const Product = React.lazy(() => import("./components/Product"));
const ShoppingCart = React.lazy(() => import("./components/ShoppingCart"));
const Account = React.lazy(() => import("./components/Account"));
const Page404 = React.lazy(() => import("./components/Page404"));
const Forget = React.lazy(() => import("./components/Forget"));
const Spinner = React.lazy(() => import("./components/Spinner"));
const Wishlist = React.lazy(() => import("./components/Wishlist"));
const PaymentForm = React.lazy(() => import("./components/PaymentForm"));
const ReturnMomo = React.lazy(() => import("./components/ReturnMomo"));
const AboutUs = React.lazy(() => import("./components/AboutUs"));
const Game = React.lazy(() => import("./components/Game"));

const App = () => {
  var styles = `
    iframe {
      display: none;
    }
    .goog-te-combo {
        width:60px;
    }
  `
  return (
    <>
      <style>
        {styles}
      </style>
      <ChatBox />
      <ToastContainer />
      <Router>
        <Suspense
          fallback={
            <CircularProgress
              disableShrink
              style={{
                position: "absolute",
                top: "50%",
                left: "50%",
              }}
            />
          }
        >
          <DefaultLayout>
            <CssBaseline />
            <ScrollOnTop>
              <Switch>
                <Route path={["/", "/home"]} exact>
                  <Home />
                </Route>
                <Route path={"/dessert"} exact>
                  <Dessert />
                </Route>
                <Route path={"/milktea"} exact>
                  <Milktea />
                </Route>
                <Route path={"/product"} exact>
                  <Product />
                </Route>
                <Route path={"/signin"} exact>
                  <SignIn />
                </Route>
                <Route path={"/signup"} exact>
                  <SignUp />
                </Route>
                <Route path={"/forget"} exact>
                  <Forget />
                </Route>
                <Route path={"/spinner"} exact>
                  <Spinner />
                </Route>
                <Route path={"/milktea"} exact>
                  <Milktea />
                </Route>
                <Route path={"/about"} exact>
                  <AboutUs />
                </Route>

                <PrivateRoute
                  path={["/account", "/account/history", "/account/voucher"]}
                >
                  <Account />
                </PrivateRoute>

                <PrivateRoute path={"/shoppingcart"}>
                  <ShoppingCart />
                </PrivateRoute>

                <PrivateRoute path={"/wishlist"}>
                  <Wishlist />
                </PrivateRoute>

                <PrivateRoute path={"/checkout"}>
                  <CheckAddress />
                </PrivateRoute>

                <PrivateRoute path={"/payment"}>
                  <PaymentForm />
                </PrivateRoute>

                <PrivateRoute path={"/checkoutresult"}>
                  <ReturnMomo />
                </PrivateRoute>

                <PrivateRoute path={"/checkoutresult-vnpay"}>
                  <ReturnVNPay />
                </PrivateRoute>

                <Route path="/*">
                  <Page404 />
                </Route>
              </Switch>
            </ScrollOnTop>
          </DefaultLayout>
        </Suspense>
      </Router>
    </>
  );
};

export default App;
