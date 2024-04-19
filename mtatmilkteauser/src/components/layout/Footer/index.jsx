import { Grid, Typography } from "@material-ui/core";
import GoogleMap from "../../../common/GoogleMap";

const Footer = () => {
  return (
    <Grid
      container
      style={{
        paddingTop: 10,
        paddingBottom: 5,
        backgroundColor: "#416c48",
        color: "white",
        minHeight: "calc(100vh - 577px)",
      }}
    >
      <Grid item xs={12} md={8} style={{ paddingLeft: 20 + "px" }}>
        <Typography>
          <b>Trụ sở chính: </b>
          Công ty CP MTATMilkTea - ĐKKD: 0316 871719 do sở KHĐT TP Hà Nội cấp lần
          đầu ngày 01/01/2024
        </Typography>
        <Typography>
          <b>Nhà máy: </b>
          Lô 6, CN5, Cụm CN Ngọc Hồi, Thanh Trì , Hà Nội
        </Typography>
        <Typography>
          <b>Địa chỉ: </b>
          Ngõ 421, Hoàng Quốc Việt, Cổ Nhuế 1
        </Typography>
        <Typography>
          <b>Điện thoại: </b>
          028 6263 0377 - 6263 0378
        </Typography>
        <Typography>
          <b>Email: </b>
          Info@MTATMilkTea.com.vn
        </Typography>
      </Grid>

      <Grid
        item
        xs={12}
        md={4}
        style={{ display: "flex", justifyContent: "center" }}
      >
        <GoogleMap />
      </Grid>
    </Grid>
  );
};

export default Footer;
