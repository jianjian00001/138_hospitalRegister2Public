<template>
  <div>
    <div ref="sex" style="width: 1000px; height: 400px"></div>
    <div ref="position" style="width: 1000px; height: 400px"></div>
    <div ref="dept" style="width: 1000px; height: 400px"></div>
  </div>
</template>

<script>
import echarts from "echarts";
import {
  getAppointmentBySpecial,
  qryUserStatisGroupBySex
} from "@/api/patient";
export default {
  mounted() {
    this.qryUserStatisGroupBySex();
    this.getAppointmentBySpecial();
    //this.dept();
  },
  methods: {
    qryUserStatisGroupBySex() {
      const chartDom = this.$refs.sex;
      const myChart = echarts.init(chartDom);

      qryUserStatisGroupBySex().then(response => {
        // 配置图表选项和数据
        const option = {
          tooltip: {
            trigger: "item"
          },
          title: {
            text: "就诊卡男女比例"
          },
          series: [
            {
              name: "人数",
              type: "pie",
              radius: "50%",
              data: response.data
            }
          ]
        };
        // 使用刚指定的配置项和数据显示图表
        myChart.setOption(option);
      });
    },
    getAppointmentBySpecial() {
      const chartDom2 = this.$refs.position;
      const myChart2 = echarts.init(chartDom2);
      // 基于准备好的dom，初始化echarts实例
      //let myChart = this.$echarts.init(document.getElementById("myChart"));
      getAppointmentBySpecial().then(response => {
        // 绘制图表
        const option = {
          title: { text: "科室预约人数统计" },
          tooltip: {},
          xAxis: {
            data: response.data.name
          },
          yAxis: [
            {
              type: "value",
              minInterval: 1
            }
          ],
          series: [
            {
              color: "#1f7ae1",
              name: "人数",
              type: "bar",
              barWidth: 60,
              data: response.data.value
            }
          ]
        };
        myChart2.setOption(option);
      });
    },
    dept() {
      const chartDom2 = this.$refs.dept;
      const myChart2 = echarts.init(chartDom2);
      // 基于准备好的dom，初始化echarts实例
      //let myChart = this.$echarts.init(document.getElementById("myChart"));
      clerk.qryUserStatisGroupByDept().then(response => {
        // 绘制图表
        const option = {
          title: { text: "部门人数统计" },
          tooltip: {},
          xAxis: {
            data: response.data.deptList.name
          },
          yAxis: [
            {
              type: "value",
              minInterval: 1
            }
          ],
          series: [
            {
              data: response.data.deptList.value,
              name: "部门人数",
              type: "bar",
              itemStyle: {
                normal: {
                  color: function(params) {
                    // 给出颜色组
                    let colorList = [
                      "#FA897B",
                      "#FFDD94",
                      "#97d224",
                      "#e31a67",
                      "#4675C0",
                      "#B8BFD6",
                      "#FFA876"
                    ];
                    return colorList[params.dataIndex];
                  }
                }
              },
              color: "#1f7ae1"
            }
          ]
        };
        myChart2.setOption(option);
      });
    }
  }
};
</script>
