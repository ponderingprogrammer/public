﻿using System;
using System.Collections.Generic;
using System.Diagnostics;
using System.Drawing;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Mvc;
using Microsoft.Extensions.Logging;
using PonderingProgrammer.NProcGen.Web.Models;
using Svg;

namespace PonderingProgrammer.NProcGen.Web.Controllers
{
    public class HomeController : Controller
    {
        private readonly ILogger<HomeController> _logger;
        private readonly GeneratorService _service = new GeneratorService();

        public HomeController(ILogger<HomeController> logger)
        {
            _logger = logger;
        }

        public IActionResult Index()
        {
            return View();
        }

        public IActionResult Privacy()
        {
            return View();
        }

        [ResponseCache(Duration = 0, Location = ResponseCacheLocation.None, NoStore = true)]
        public IActionResult Error()
        {
            return View(new ErrorViewModel {RequestId = Activity.Current?.Id ?? HttpContext.TraceIdentifier});
        }

        public IActionResult GenerateMap()
        {
            var map = _service.GenerateSampleMap();
            var cellSize = 50;
            var boundarySvg = new SvgRectangle
            {
                X = 0, 
                Y = 0, 
                Width = map.GetBounds().Width * cellSize, 
                Height = map.GetBounds().Height * cellSize,
                Fill = new SvgColourServer(Color.Aqua),
                Stroke = new SvgColourServer(Color.Black)
            };

            var viewModel = new MapViewModel();
            viewModel.Svg = boundarySvg.GetXML();
            return View("Index", viewModel);
        }
    }
}
